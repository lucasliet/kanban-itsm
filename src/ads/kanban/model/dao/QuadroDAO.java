package ads.kanban.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.kanban.model.entity.QuadroEntity;
import ads.kanban.model.entity.UsuarioEntity;

public class QuadroDAO {
    public QuadroEntity buscarQuadro(int id) throws IOException {
        QuadroEntity quadro = new QuadroEntity();
        String sql = "SELECT id, titulo FROM quadros WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);){

            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery();) {

                while (rs.next()) {
                    quadro.setId(id);
                    quadro.setTitulo(rs.getString("titulo"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        return quadro;
    }

    public int deletarQuadro(int id) throws IOException {
        int feedback = -1;
        QuadroEntity quadro = new QuadroEntity();
        String sql = "DELETE FROM quadros WHERE id = ?";
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);){
                pst.setInt(1, id);
                pst.execute();
                feedback = 1;
        }catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        if (feedback == -1){
            System.out.println("Operação falhou");
        }else{
            System.out.println("Quadro " + quadro.getTitulo() + " de ID " + quadro.getId() + " foi removido com sucesso");
        }
        return feedback;
    }

    public int inserirQuadro(QuadroEntity quadro, UsuarioEntity usuario) throws IOException {
        int id = -1;
        String sql = "INSERT INTO quadros (titulo) VALUES (?)";

        try (Connection conn = ConnectionFactory.getConnection(); 
        		PreparedStatement pst = conn.prepareStatement(sql);) {

            pst.setString(1, quadro.getTitulo());
            pst.execute();

            // obter o id criado
            String query = "SELECT LAST_INSERT_ID()";
            try (PreparedStatement pst1 = conn.prepareStatement(query); 
            		ResultSet rs = pst1.executeQuery();) {

                if (rs.next()) {
                    id = rs.getInt(1);
                    quadro.setId(id);
                }

                //Insere na tabela n pra n a relação de quadro e usuario
                String sqlUser = "INSERT INTO quadros_usuarios (id_usuario, id_quadro) VALUES(?,?);";
                try (PreparedStatement pst2 = conn.prepareStatement(sqlUser);){
                    pst2.setInt(1, usuario.getId());
                    pst2.setInt(2, quadro.getId());
                    pst2.execute();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        System.out.println("Quadro " + quadro.getTitulo() + " de ID " + quadro.getId() + " gerado no banco com sucesso");
        return id;
    }

    public void atualizarQuadro(QuadroEntity quadro) throws IOException {
        String sql = "UPDATE quadros SET titulo = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection(); 
        		PreparedStatement pst = conn.prepareStatement(sql);) {
            pst.setString(1, quadro.getTitulo());
            pst.setInt(2, quadro.getId());
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }
    
    public ArrayList<QuadroEntity> listarQuadros(int usuarioId) throws IOException {
		String sql ="SELECT q.id, q.titulo FROM quadros q " +
                        "JOIN quadros_usuarios qu ON q.id = qu.id_quadro " +
                        "JOIN usuarios u ON u.id = qu.id_usuario " +
                        "WHERE u.id = ? " +
                        "ORDER BY titulo";
		ArrayList<QuadroEntity> quadros = new ArrayList<>();
		
		try (Connection conn = ConnectionFactory.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(sql)){
		    pst.setInt(1, usuarioId);

			try (ResultSet rs = pst.executeQuery();){
				
				while(rs.next()) {
					QuadroEntity quadro = new QuadroEntity();
					quadro.setId(rs.getInt("id"));
					quadro.setTitulo(rs.getString("titulo"));
					quadros.add(quadro);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
    	
    	return quadros;
    	
    }
    //Mesma lista de cima mas setando um limite de resultados pra tela Home
    public ArrayList<QuadroEntity> ultimosQuadros(int usuarioId, int limit) throws IOException {
        String sql ="SELECT q.id, q.titulo FROM quadros q " +
                        "JOIN quadros_usuarios qu ON q.id = qu.id_quadro " +
                        "JOIN usuarios u ON u.id = qu.id_usuario " +
                        "WHERE u.id = ? " +
                        "ORDER BY id DESC LIMIT ?";
        ArrayList<QuadroEntity> quadros = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setInt(1, usuarioId);
            pst.setInt(2,limit);

            try (ResultSet rs = pst.executeQuery();){

                while(rs.next()) {
                    QuadroEntity quadro = new QuadroEntity();
                    quadro.setId(rs.getInt("id"));
                    quadro.setTitulo(rs.getString("titulo"));
                    quadros.add(quadro);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        return quadros;

    }
}

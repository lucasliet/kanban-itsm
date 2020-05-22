package ads.kanban.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.kanban.model.entity.ColunaEntity;
import ads.kanban.model.entity.QuadroEntity;
import ads.kanban.model.service.QuadroService;

public class ColunaDAO {
	
	public ColunaEntity buscarColuna(int id) throws IOException {
        ColunaEntity coluna = new ColunaEntity();
        String sql = "SELECT id, titulo, id_quadro FROM colunas c, quadros q"
        		+ "WHERE c.id_quadro = q.id AND c.id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);){

            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery();) {

                while (rs.next()) {
                    coluna.setId(id);
                    coluna.setTitulo(rs.getString("titulo"));
                    QuadroEntity quadro = new QuadroEntity();
                    quadro.setId(rs.getInt("id"));
                    quadro.setTitulo(rs.getString("titulo"));
                    coluna.setQuadro(quadro);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        return coluna;
	}
	
	public int deletarColuna(int id) throws IOException {
        int feedback = -1;
        ColunaEntity coluna = new ColunaEntity();
        String sql = "DELETE FROM colunas WHERE id = ?";
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);){
                coluna = buscarColuna(id);
        		pst.setInt(1, id);
                pst.execute();
                feedback = 1;
        }catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        if (feedback == -1){
            System.out.println("Operacao falhou");
        }else{
            System.out.println("Coluna " + coluna.getTitulo() + coluna.getId() + " foi removida com sucesso");
        }
        return feedback;
    
	}

    public int inserirColuna(ColunaEntity coluna) throws IOException {
        int id = -1;
        String sql = "INSERT INTO colunas (titulo , id_quadro) VALUES (?,?)";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql);) {

            pst.setString(1, coluna.getTitulo());
            pst.setInt(2, coluna.getQuadro().getId());
            pst.execute();

            // obter o id criado
            String query = "SELECT LAST_INSERT_ID()";
            try (PreparedStatement pst1 = conn.prepareStatement(query);
                    ResultSet rs = pst1.executeQuery();) {

                if (rs.next()) {
                    id = rs.getInt(1);
                    coluna.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        System.out.println("Coluna " + coluna.getTitulo() + " de ID " + coluna.getId() + " gerado no banco com sucesso");
        return id;
    }

    public ColunaEntity atualizarColuna(ColunaEntity coluna) throws IOException {
        String sql = "UPDATE colunas SET titulo = ?, quadro_id = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql);) {
            pst.setString(1, coluna.getTitulo());
            pst.setInt(2, coluna.getQuadro().getId());
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
            ColunaEntity colunaAlterada = buscarColuna(coluna.getId());
        return colunaAlterada;
    }

    public ArrayList<ColunaEntity> listarColunas(int quadroId) throws IOException {
        String sql ="SELECT c.id, c.titulo, c.id_quadro FROM colunas c" +
                " JOIN quadros q ON q.id = c.id_quadro" +
                " WHERE q.id = ?" +
                " ORDER BY c.titulo";
        ArrayList<ColunaEntity> colunas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)){

            pst.setInt(1, quadroId);
            pst.execute();

            try (ResultSet rs = pst.executeQuery();){

                while(rs.next()) {
                    ColunaEntity coluna = new ColunaEntity();
                    coluna.setId(rs.getInt("id"));
                    coluna.setTitulo(rs.getString("titulo"));
                    QuadroDAO qDAO = new QuadroDAO();
                    QuadroEntity quadro = qDAO.buscarQuadro(rs.getInt("id_quadro"));
                    coluna.setQuadro(quadro);
                    colunas.add(coluna);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        return colunas;
    }
}
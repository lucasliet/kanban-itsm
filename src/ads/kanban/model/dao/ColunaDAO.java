package ads.kanban.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ads.kanban.model.entity.ColunaEntity;

public class ColunaDAO {
	
	public ColunaEntity buscarColuna(int id) throws IOException {
        ColunaEntity coluna = new ColunaEntity();
        String sql = "SELECT id, titulo FROM colunas WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);){

            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery();) {

                while (rs.next()) {
                    coluna.setId(id);
                    coluna.setTitulo(rs.getString("titulo"));
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
            System.out.println("Coluna " + coluna.getTitulo() + coluna.getId() + " foi removida com sucesso");
        }
        return feedback;
    
	}
        public int inserirColuna(ColunaEntity coluna) throws IOException {
            int id = -1;
            String sql = "INSERT INTO colunas (titulo) VALUES (?)";

            try (Connection conn = ConnectionFactory.getConnection(); 
            		PreparedStatement pst = conn.prepareStatement(sql);) {

                pst.setString(1, coluna.getTitulo());
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
            String sql = "UPDATE colunas SET titulo = ? WHERE id = ?";
            try (Connection conn = ConnectionFactory.getConnection(); 
            		PreparedStatement pst = conn.prepareStatement(sql);) {
                pst.setString(1, coluna.getTitulo());
                pst.setInt(2, coluna.getId());
                pst.execute();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
                ColunaEntity colunaAlterada = buscarColuna(coluna.getId());
            return colunaAlterada;
	}	
}
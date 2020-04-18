package ads.kanban.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ads.kanban.model.entity.ComentarioEntity;

public class ComentarioDAO {
	public int deletarComentario(int id) throws IOException {
        int feedback = -1;
        ComentarioEntity coment = new ComentarioEntity();
        String sql = "DELETE FROM comentarios WHERE id = ?";
        
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
            System.out.println("Corpo " + coment.getCorpo() + " de ID " + coment.getIdComentario()+ " foi removido com sucesso");
        }
        return feedback;
    }

    public int inserircomentario(ComentarioEntity coment) throws IOException {
        int id = -1;
        String sql = "INSERT INTO comentarios (corpo, id_usuario, id_ticket) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection(); 
        		PreparedStatement pst = conn.prepareStatement(sql);) {

            pst.setString(1, coment.getCorpo());
            pst.execute();

            // obter o id criado
            String query = "SELECT LAST_INSERT_ID()";
            try (PreparedStatement pst1 = conn.prepareStatement(query); 
            		ResultSet rs = pst1.executeQuery();) {

                if (rs.next()) {
                    id = rs.getInt(1);
                    coment.setIdComentario(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        System.out.println("comentario " + coment.getCorpo() + " de ID " + coment.getIdComentario() + " gerado no banco com sucesso");
        return id;
    }
}

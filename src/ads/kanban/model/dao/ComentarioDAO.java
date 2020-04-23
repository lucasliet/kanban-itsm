package ads.kanban.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ads.kanban.model.entity.ComentarioEntity;
import ads.kanban.model.entity.TicketEntity;
import ads.kanban.model.entity.UsuarioEntity;

public class ComentarioDAO {
	 public ComentarioEntity buscarComentario(int id) throws IOException {
		 
	        ComentarioEntity comentario = new ComentarioEntity();
	        String sql = "SELECT id, corpo FROM comentarios c, usuarios u, WHERE id = ?";

	        try (Connection conn = ConnectionFactory.getConnection();
	             PreparedStatement pst = conn.prepareStatement(sql);){

	            pst.setInt(1, id);
	            try (ResultSet rs = pst.executeQuery();) {

	                while (rs.next()) {
	                    comentario.setId(rs.getInt("id"));
	                    comentario.setCorpo(rs.getString("corpo"));
	                    TicketEntity ticket = new TicketEntity();
	        			ticket.setId(rs.getInt("id"));
		                ticket.setTitulo(rs.getString("titulo"));
		                ticket.setDescricao(rs.getString("descricao"));
		                ticket.setFoto(rs.getString("foto"));
		                UsuarioEntity usuario = new UsuarioEntity();
		                usuario.setId(id);
	                    usuario.setNome(rs.getString("nome"));
	                    usuario.setUltimoNome(rs.getString("ultimo_nome"));
	                    usuario.setEndereco(rs.getString("endereco"));
	                    usuario.setTelefone(rs.getString("telefone"));
	                    usuario.setEmail(rs.getString("email"));
	                    comentario.setTicket(ticket);
	                    comentario.setUsuario(usuario);
	                
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	                throw new IOException(e);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new IOException(e);
	        }
	        return comentario;
	 }
	
	public int deletarComentario(int id) throws IOException {
        int feedback = -1;
        ComentarioEntity coment = new ComentarioEntity();
        String sql = "DELETE FROM comentarios WHERE id = ?";
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);){
                coment = buscarComentario(id);
        		pst.setInt(1, id);
                pst.execute();
                feedback = 1;
        }catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        if (feedback == -1){
            System.out.println("Opera��o falhou");
        }else{
            System.out.println("Corpo " + coment.getCorpo() + " de ID " + coment.getId()+ " foi removido com sucesso");
        }
        return feedback;
    }

    public int inserircomentario(ComentarioEntity coment) throws IOException {
        int id = -1;
        String sql = "INSERT INTO comentarios (corpo, id_usuario, id_ticket) VALUES = (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection(); 
        		PreparedStatement pst = conn.prepareStatement(sql);) {

            pst.setString(1, coment.getCorpo());
            pst.setInt(2, coment.getUsuario().getId());
            pst.setInt(3, coment.getTicket().getId());
            pst.execute();

            String query = "SELECT LAST_INSERT_ID()";
            try (PreparedStatement pst1 = conn.prepareStatement(query); 
            		ResultSet rs = pst1.executeQuery();) {

                if (rs.next()) {
                    id = rs.getInt(1);
                    coment.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        System.out.println("comentario " + coment.getCorpo() + " de ID " + coment.getId() + " gerado no banco com sucesso");
        return id;
    }
}

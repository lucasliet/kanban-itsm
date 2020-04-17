package ads.kanban.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ads.kanban.model.entity.ColunaEntity;
import ads.kanban.model.entity.TicketEntity;


public class TicketDAO {
	public TicketEntity buscarTicket(int id) throws IOException {
		TicketEntity  ticket = new TicketEntity();
		String sql = "SELECT id, titulo, descricao, foto, id_coluna, titulo "
				+ "FROM TICKETS t, COLUNAS c "
				+ "WHERE t.id_colunas = c.id AND t.id = ?";
		
		try (Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);){
					
			pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery();) {
			
				while (rs.next()) {
	                ticket.setId(rs.getInt("id"));
	                ticket.setTitulo(rs.getString("titulo"));
	                ticket.setDescricao(rs.getString("descricao"));
	                ticket.setFoto(rs.getString("foto"));
	                ColunaEntity coluna = new ColunaEntity();
	                coluna.setId(rs.getInt("id"));
	                coluna.setString(rs.getString("titulo"));
	                ticket.setColunaEntity(coluna);
				}
			} catch (SQLException e) {
	            e.printStackTrace();
	            throw new IOException(e);
	        }
		} catch (SQLException e) {
	        e.printStackTrace();
	        throw new IOException(e);
	    }
		return ticket;
	}
}

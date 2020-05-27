package ads.kanban.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.kanban.model.entity.ColunaEntity;
import ads.kanban.model.entity.TicketEntity;


public class TicketDAO {
	public ArrayList<TicketEntity> listarTickets(int colunaId) throws IOException {
		ArrayList<TicketEntity> tickets = new ArrayList<>();
		String sql = "SELECT  t.id, t.titulo, t.descricao, t.foto FROM tickets t " +
				 	 	"JOIN colunas c ON t.id_coluna = c.id " +
					 	"JOIN usuarios_tickets ut ON ut.id_ticket = t.id " +
					 	"JOIN usuarios u ON ut.id_usuario = u.id "+
					 	"WHERE c.id = ? " +
					 	"ORDER BY t.titulo";

		try (Connection conn = ConnectionFactory.getConnection();
			 PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, colunaId);
			pst.execute();

			try (ResultSet rs = pst.executeQuery();){
			
			while(rs.next()) {
				TicketEntity ticket = new TicketEntity();
				ticket.setId(rs.getInt("id"));
				ticket.setTitulo(rs.getString("titulo"));
				ticket.setDescricao(rs.getString("descricao"));
				ticket.setFoto(rs.getString("foto"));
				ColunaEntity coluna = new ColunaEntity();
				coluna.setId(rs.getInt("id"));
				coluna.setTitulo(rs.getString("titulo"));
				ticket.setColuna(coluna);
				tickets.add(ticket);
			}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	
		return tickets;
	}
	
	public TicketEntity buscarTicket(int id) throws IOException {
		TicketEntity  ticket = new TicketEntity();
		String sql = "SELECT t.id, t.titulo, t.descricao, t.foto, c.id, c.titulo "
				+ "FROM tickets t "
				+ "JOIN colunas c ON t.id_coluna = c.id "
				+ "WHERE t.id = ?";
		
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
	                coluna.setTitulo(rs.getString("titulo"));
	                ticket.setColuna(coluna);
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
	
	public int  deletarTicket(int id) throws IOException {
		int feedback = -1;
		TicketEntity ticket = new TicketEntity();
		String sql = "DELETE FROM tickets WHERE id = ? ";
		
		try(Connection conn = ConnectionFactory.getConnection();
		    PreparedStatement pst = conn.prepareStatement(sql);){
				ticket = buscarTicket(id);
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
            System.out.println("Ticket " + ticket.getTitulo()+" de ID " + ticket.getId()+" foi removido com sucesso");
        }
        return feedback;
	}
	
	public int inserirTicket(TicketEntity ticket) throws IOException {
		int id = -1;
		String sql = "INSERT INTO tickets (titulo, descricao, foto, id_coluna)"
				+ " VALUES (?, ?, ?, ?) ";
		
		try (Connection conn = ConnectionFactory.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(sql);) {
			
			pst.setString(1, ticket.getTitulo());
			pst.setString(2, ticket.getDescricao());
			pst.setString(3, ticket.getFoto());
			pst.setInt(4, ticket.getColuna().getId());
			pst.execute();
			
			// obter o id criado
            String query = "select LAST_INSERT_ID()";
            try (PreparedStatement pst1 = conn.prepareStatement(query); ResultSet rs = pst1.executeQuery();) {

                if (rs.next()) {
                    id = rs.getInt(1);
                    ticket.setId(id);
                }
            }
		} catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        System.out.println("Ticket " + ticket.getTitulo()+" de ID " + ticket.getId()+" gerado no banco com sucesso");
        return id;
	}
	
	public TicketEntity atualizarTicket(TicketEntity ticket) throws IOException {
		String sql = "UPDATE tickets SET titulo = ?, descricao = ?, foto = ?, "
				+ "id_coluna = ? WHERE id = ?";
		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setString(1, ticket.getTitulo());
			pst.setString(2, ticket.getDescricao());
			pst.setString(3, ticket.getFoto());
			pst.setInt(4, ticket.getColuna().getId());
			pst.setInt(5, ticket.getId());
            pst.execute();

		 } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
	     }
	        TicketEntity ticketAlterado = buscarTicket(ticket.getId());
	     return ticketAlterado;
	}
}

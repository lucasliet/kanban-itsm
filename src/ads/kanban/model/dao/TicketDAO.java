package ads.kanban.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.kanban.model.entity.ColunaEntity;
import ads.kanban.model.entity.QuadroEntity;
import ads.kanban.model.entity.TicketEntity;


public class TicketDAO {

	public ArrayList<TicketEntity> ultimosTickets(int usuarioId, int limit) throws IOException {
		ArrayList<TicketEntity> tickets = new ArrayList<>();
		String sql = "SELECT  t.id, t.titulo, t.descricao, t.foto FROM tickets t " +
						"JOIN usuarios_tickets ut ON ut.id_ticket = t.id " +
						"JOIN usuarios u ON ut.id_usuario = u.id "+
						"WHERE u.id = ? " +
						"ORDER BY t.id DESC LIMIT ?";

		try (Connection conn = ConnectionFactory.getConnection();
				 PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, usuarioId);
			pst.setInt(2, limit);
			pst.execute();

			try (ResultSet rs = pst.executeQuery();){

				while(rs.next()) {
					TicketEntity ticket = new TicketEntity();
					ticket.setId(rs.getInt("id"));
					ticket.setTitulo(rs.getString("titulo"));
					ticket.setDescricao(rs.getString("descricao"));
					ticket.setFoto(rs.getString("foto"));
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
		String sql = "SELECT t.id, t.titulo, t.descricao, t.foto, c.id, c.titulo, q.id, q.titulo FROM tickets t " +
				 		"JOIN colunas c ON t.id_coluna = c.id " +
						"JOIN quadros q ON c.id_quadro = q.id " +
				 		"WHERE t.id = ?";
		
		try (Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);){
					
			pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery();) {
			
				while (rs.next()) {
	                ticket.setId(rs.getInt("t.id"));
	                ticket.setTitulo(rs.getString("t.titulo"));
	                ticket.setDescricao(rs.getString("t.descricao"));
	                ticket.setFoto(rs.getString("t.foto"));
	                ColunaEntity coluna = new ColunaEntity();
	                coluna.setId(rs.getInt("c.id"));
	                coluna.setTitulo(rs.getString("c.titulo"));
					QuadroEntity quadro = new QuadroEntity();
					quadro.setId(rs.getInt("q.id"));
					quadro.setTitulo(rs.getString("q.titulo"));
					coluna.setQuadro(quadro);
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
	
	public boolean deletarTicket(int id) throws IOException {
		boolean deletou = false;
		TicketEntity ticket = new TicketEntity();
		String sql = "DELETE FROM tickets WHERE id = ? ";
		
		try(Connection conn = ConnectionFactory.getConnection();
		    PreparedStatement pst = conn.prepareStatement(sql);){
				ticket = buscarTicket(id);
				pst.setInt(1, id);
				pst.execute();
				deletou = true;
		}catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        if (deletou){
            System.out.println("Ticket " + ticket.getTitulo()+" de ID " + ticket.getId()+" foi removido com sucesso");
        }else{
            System.out.println("Operação falhou");
        }
        return deletou;
	}
	
	public int inserirTicket(TicketEntity ticket, int usuarioId) throws IOException {
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

			//insere na tabela usuarios_tickets, o ticket que acaba de ser criado, e o usuário que criou
			String npran = "INSERT INTO usuarios_tickets (id_usuario, id_ticket) VALUES ( ? , ? )";
			try(PreparedStatement pstn = conn.prepareStatement(npran);){
				pstn.setInt(1,usuarioId);
				pstn.setInt(2,ticket.getId());
				pstn.execute();
			}

		} catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        System.out.println("Ticket " + ticket.getTitulo()+" de ID " + ticket.getId()+" gerado no banco com sucesso");
        return id;
	}

	public void moverTicket(int colunaId, int ticketId) throws  IOException {
		String sql = "UPDATE tickets SET id_coluna = ? WHERE id = ?";

		try (Connection conn = ConnectionFactory.getConnection();
			 PreparedStatement pst = conn.prepareStatement(sql);){
				pst.setInt(1, colunaId);
				pst.setInt(2, ticketId);
				pst.execute();
		} catch (SQLException e){
			e.printStackTrace();
			throw new IOException(e);
		}
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

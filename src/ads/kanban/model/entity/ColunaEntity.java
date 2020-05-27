package ads.kanban.model.entity;

import sun.security.krb5.internal.Ticket;

import java.util.ArrayList;

public class ColunaEntity {
	private int id;
	private String titulo;
	private QuadroEntity quadro;
	private ArrayList<TicketEntity> tickets;
	
	public QuadroEntity getQuadro() {
		return quadro;
	}

	public void setQuadro(QuadroEntity quadro) {
		this.quadro = quadro;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public ArrayList<TicketEntity> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<TicketEntity> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "ColunaEntity [id_coluna=" + id + ", titulo_coluna=" + titulo + ", quadro=" + quadro + "]";
	}
}

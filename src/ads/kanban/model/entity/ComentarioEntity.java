package ads.kanban.model.entity;

public class ComentarioEntity {
	private int id;
	private String corpo;
	private UsuarioEntity usuario;
	private TicketEntity ticket;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCorpo() {
		return corpo;
	}
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	public int getTicket() {
		return ticket;
	}
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
	@Override
	public String toString() {
		return "ComentarioEntity [idQuadro=" + id + ", corpo=" + corpo + ", idUsuario=" + usuario
				+ ", idTicket=" + Ticket + "]";
	}
	
}

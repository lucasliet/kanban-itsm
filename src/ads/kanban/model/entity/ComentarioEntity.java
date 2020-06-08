package ads.kanban.model.entity;

public class ComentarioEntity {
	private int id;
	private String corpo;
	private UsuarioEntity usuario;
	private TicketEntity ticket;
	private int curtidas;

	public int getCurtidas() { return curtidas;	}

	public void setCurtidas(int curtidas) { this.curtidas = curtidas; }

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

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public TicketEntity getTicket() {
		return ticket;
	}

	public void setTicket(TicketEntity ticket) {
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		return "ComentarioEntity{" +
				"id=" + id +
				", corpo='" + corpo + '\'' +
				", usuario=" + usuario +
				", ticket=" + ticket +
				", curtidas=" + curtidas +
				'}';
	}
}

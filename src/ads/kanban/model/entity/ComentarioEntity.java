package ads.kanban.model.entity;

public class ComentarioEntity {
	private int idComentario;
	private String corpo;
	private int idUsuario;
	private int idTicket;
	
	
	public int getIdComentario() {
		return idComentario;
	}
	public void setIdComentario(int idComentario) {
		this.idComentario = idComentario;
	}
	public String getCorpo() {
		return corpo;
	}
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdTicket() {
		return idTicket;
	}
	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}
	@Override
	public String toString() {
		return "ComentarioEntity [idQuadro=" + idComentario + ", corpo=" + corpo + ", idUsuario=" + idUsuario
				+ ", idTicket=" + idTicket + "]";
	}
	
}

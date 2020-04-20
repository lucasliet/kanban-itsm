package ads.kanban.model.entity;

public class ColunaEntity {
	private int id_coluna;
	private String titulo_coluna;
	private QuadroEntity quadro;
	
	public int getId() {
		return id_coluna;
	}
	public void setId(int id) {
		this.id_coluna = id;
	}
	public String getTitulo() {
		return titulo_coluna;
	}
	public void setTitulo(String titulo) {
		this.titulo_coluna = titulo;
	}
	
	public String toString() {
		return "ColunaEntity [id=" + id_coluna + "titulo=" + titulo_coluna +"]";
		
	}
}

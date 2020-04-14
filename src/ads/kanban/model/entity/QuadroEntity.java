package ads.kanban.model.entity;

public class QuadroEntity {
	private int id;
	private String titulo;
	
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
	
	@Override
	public String toString() {
		return "QuadroEntity [id=" + id + ", titulo=" + titulo + "]";
	}
}
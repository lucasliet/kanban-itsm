package ads.kanban.model.entity;

public class ColunaEntity {
	private int id;
	private String titulo;
	private QuadroEntity quadro;
	
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
	@Override
	public String toString() {
		return "ColunaEntity [id_coluna=" + id + ", titulo_coluna=" + titulo + ", quadro=" + quadro + "]";
	}
}

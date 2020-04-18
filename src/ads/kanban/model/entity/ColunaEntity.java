package ads.kanban.model.entity;

public class ColunaEntity {
	private int id_coluna;
	private String titulo_coluna;
	private QuadroEntity quadro;
	
	public QuadroEntity getQuadro() {
		return quadro;
	}
	public void setQuadro(QuadroEntity quadro) {
		this.quadro = quadro;
	}
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
	@Override
	public String toString() {
		return "ColunaEntity [id_coluna=" + id_coluna + ", titulo_coluna=" + titulo_coluna + ", quadro=" + quadro + "]";
	}
	
}

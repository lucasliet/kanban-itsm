package ads.kanban.model.entity;

import java.util.ArrayList;

public class TicketEntity {
	private int id;
	private String titulo;
	private String descricao;
	private String foto;
	private ColunaEntity coluna;
	private ArrayList<ComentarioEntity> comentarios;
	
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
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public ColunaEntity getColuna() {
		return coluna;
	}
	
	public void setColuna(ColunaEntity coluna) {
		this.coluna = coluna;
	}

	public ArrayList<ComentarioEntity> getComentarios() {
		return comentarios;
	}

	public void setComentarios(ArrayList<ComentarioEntity> comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public String toString() {
		return "TicketEntity{" +
				"id=" + id +
				", titulo='" + titulo + '\'' +
				", descricao='" + descricao + '\'' +
				", foto='" + foto + '\'' +
				", coluna=" + coluna +
				", comentarios=" + comentarios +
				'}';
	}
}

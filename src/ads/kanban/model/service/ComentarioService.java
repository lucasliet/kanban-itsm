package ads.kanban.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.kanban.model.dao.ComentarioDAO;
import ads.kanban.model.entity.ComentarioEntity;

public class ComentarioService {
	private ComentarioDAO dao;

	public ComentarioService(){this.dao = new ComentarioDAO();}

	public ComentarioEntity buscarComentario(int idComentario) throws IOException {
		return dao.buscarComentario(idComentario);
	}

	public void excluirComentario(int id) throws IOException {
    	dao.deletarComentario(id);
    }

	public int inserirComentario(ComentarioEntity coment) throws IOException {
    	return dao.inserirComentario(coment);
    }

    public ArrayList<ComentarioEntity> listarComentarios(int idTicket) throws IOException {
		return dao.listarComentarios(idTicket);
	}

	public ArrayList<ComentarioEntity> ultimosComentarios(int idTicket, int limit) throws IOException {
		return dao.ultimosComentarios(idTicket, limit);
	}

}

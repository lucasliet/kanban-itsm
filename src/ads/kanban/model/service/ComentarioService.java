package ads.kanban.model.service;

import java.io.IOException;

import ads.kanban.model.dao.ComentarioDAO;
import ads.kanban.model.entity.ComentarioEntity;

public class ComentarioService {
	public int excluirComentario(int id) throws IOException {
    	ComentarioDAO dao = new ComentarioDAO();
    	return dao.deletarComentario(id);
    }
	public int inserirComentario(ComentarioEntity coment) throws IOException {
    	ComentarioDAO dao = new ComentarioDAO();
    	return dao.inserircomentario(coment);
    }

}

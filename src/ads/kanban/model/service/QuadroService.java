package ads.kanban.model.service;

import java.io.IOException;

import ads.kanban.model.dao.QuadroDAO;
import ads.kanban.model.entity.QuadroEntity;

public class QuadroService {

    public QuadroEntity buscarQuadro(int id) throws IOException {
    	QuadroDAO dao = new QuadroDAO();
    	return dao.buscarQuadro(id);
    }
    
    public int deletarQuadro(int id) throws IOException {
    	QuadroDAO dao = new QuadroDAO();
    	return dao.deletarQuadro(id);
    }
    
    public int inserirQuadro(QuadroEntity quadro) throws IOException {
    	QuadroDAO dao = new QuadroDAO();
    	return dao.inserirQuadro(quadro);
    }
    
    public QuadroEntity atualizarQuadro(QuadroEntity quadro) throws IOException {
    	QuadroDAO dao = new QuadroDAO();
    	return dao.atualizarQuadro(quadro);
    }
}

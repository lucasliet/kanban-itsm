package ads.kanban.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.kanban.model.dao.QuadroDAO;
import ads.kanban.model.entity.QuadroEntity;
import ads.kanban.model.entity.UsuarioEntity;

public class QuadroService {
    private QuadroDAO dao;

    public QuadroService() {
        this.dao = new QuadroDAO();
    }

    public QuadroEntity buscarQuadro(int id) throws IOException {
    	return dao.buscarQuadro(id);
    }
    
    public int deletarQuadro(int id) throws IOException {
    	return dao.deletarQuadro(id);
    }
    
    public int inserirQuadro(QuadroEntity quadro, UsuarioEntity usuario) throws IOException {
    	return dao.inserirQuadro(quadro, usuario);
    }
    
    public QuadroEntity atualizarQuadro(QuadroEntity quadro) throws IOException {
    	return dao.atualizarQuadro(quadro);
    }
    
    public ArrayList<QuadroEntity> listarQuadros() throws IOException{
    	return dao.listarQuadros();
    }

    public ArrayList<QuadroEntity> listarQuadros(int limit) throws IOException{
        return dao.listarQuadros(limit);
    }
}

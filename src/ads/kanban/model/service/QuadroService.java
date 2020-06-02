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
    
    public void atualizarQuadro(QuadroEntity quadro) throws IOException {
    	dao.atualizarQuadro(quadro);
    }
    
    public ArrayList<QuadroEntity> listarQuadros(int usuarioId) throws IOException{
    	return dao.listarQuadros(usuarioId);
    }

    public ArrayList<QuadroEntity> ultimosQuadros(int usuarioId, int limit) throws IOException{
        return dao.ultimosQuadros(usuarioId ,limit);
    }
}

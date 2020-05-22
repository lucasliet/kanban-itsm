package ads.kanban.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.kanban.model.dao.ColunaDAO;
import ads.kanban.model.entity.ColunaEntity;

public class ColunaService {
	public ColunaEntity buscarColuna(int id) throws IOException {
    	ColunaDAO dao = new ColunaDAO();
    	return dao.buscarColuna(id);
    }
    
    public int deletarColuna(int id) throws IOException {
    	ColunaDAO dao = new ColunaDAO();
    	return dao.deletarColuna(id);
    }
    
    public int inserirColuna(ColunaEntity coluna) throws IOException {
    	ColunaDAO dao = new ColunaDAO();
    	return dao.inserirColuna(coluna);
    }
    
    public ColunaEntity atualizarColuna(ColunaEntity coluna) throws IOException {
    	ColunaDAO dao = new ColunaDAO();
    	return dao.atualizarColuna(coluna);
    }

	public ArrayList<ColunaEntity> listarColunas(int quadroId) throws IOException {
		ColunaDAO dao = new ColunaDAO();
		return dao.listarColunas(quadroId);
	}
}
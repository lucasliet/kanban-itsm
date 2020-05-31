package ads.kanban.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.kanban.model.dao.ColunaDAO;
import ads.kanban.model.entity.ColunaEntity;
import ads.kanban.model.entity.TicketEntity;

public class ColunaService {
	private ColunaDAO dao;

	public ColunaService(){
		this.dao = new ColunaDAO();
	}

	public ColunaEntity buscarColuna(int id) throws IOException {
    	return dao.buscarColuna(id);
    }
    
    public int deletarColuna(int id) throws IOException {
    	return dao.deletarColuna(id);
    }
    
    public int inserirColuna(ColunaEntity coluna) throws IOException {
    	return dao.inserirColuna(coluna);
    }
    
    public ColunaEntity atualizarColuna(ColunaEntity coluna) throws IOException {
    	return dao.atualizarColuna(coluna);
    }

	public ArrayList<ColunaEntity> listarColunas(int quadroId) throws IOException {
		ArrayList<ColunaEntity> colunas = dao.listarColunas(quadroId);
		//puxa a arraylist de tickets do banco e adicionar em cada coluna (se não tiver ticket fica vazia mesmo)
		TicketService tService = new TicketService();
		for (ColunaEntity item : colunas) {
			ArrayList<TicketEntity> tickets = tService.listarTickets(item.getId());
			item.setTickets(tickets);
		}
		return colunas;
	}
}
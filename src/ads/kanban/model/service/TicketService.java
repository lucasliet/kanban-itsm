package ads.kanban.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.kanban.model.dao.TicketDAO;
import ads.kanban.model.entity.TicketEntity;

public class TicketService {
	public ArrayList<TicketEntity> listarTickets() throws IOException {
        TicketDAO dao = new TicketDAO();
        return dao.listarTickets();
    }

    public TicketEntity buscarTicket(int id) throws IOException {
        TicketDAO dao = new TicketDAO();
        return dao.buscarTicket(id);
    }

    public int deletarTicket(int id) throws IOException {
        TicketDAO dao = new TicketDAO();
        return dao.deletarTicket(id);
    }
    
    public int inserirTicket(TicketEntity ticket) throws IOException {
        TicketDAO dao = new TicketDAO();
        return dao.inserirTicket(ticket);
    }

    public TicketEntity atualizarFilme(TicketEntity ticket) throws IOException {
        TicketDAO dao = new TicketDAO();
        return dao.atualizarTicket(ticket);
    }
}

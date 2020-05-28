package ads.kanban.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.kanban.model.dao.TicketDAO;
import ads.kanban.model.entity.TicketEntity;

public class TicketService {
    private TicketDAO dao;

    public TicketService() {
        this.dao = new TicketDAO();
    }

    public ArrayList<TicketEntity> ultimosTickets(int usuarioId, int limit) throws  IOException {
      return dao.ultimosTickets(usuarioId, limit);
    }

    public ArrayList<TicketEntity> listarTickets(int colunaId) throws IOException {
        return dao.listarTickets(colunaId);
    }

    public TicketEntity buscarTicket(int id) throws IOException {
        return dao.buscarTicket(id);
    }

    public int deletarTicket(int id) throws IOException {
        return dao.deletarTicket(id);
    }
    
    public int inserirTicket(TicketEntity ticket, int usuarioId) throws IOException {
        return dao.inserirTicket(ticket, usuarioId);
    }

    public TicketEntity atualizarFilme(TicketEntity ticket) throws IOException {
        return dao.atualizarTicket(ticket);
    }
}

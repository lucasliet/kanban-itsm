package ads.kanban.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.kanban.model.dao.TicketDAO;
import ads.kanban.model.entity.ComentarioEntity;
import ads.kanban.model.entity.TicketEntity;

public class TicketService {
    private TicketDAO dao;

    public TicketService() {
        this.dao = new TicketDAO();
    }

    public ArrayList<TicketEntity> ultimosTickets(int usuarioId, int limit) throws  IOException {
        ArrayList<TicketEntity> tickets = dao.ultimosTickets(usuarioId, limit);
        ComentarioService cService = new ComentarioService();
        for(TicketEntity ticket : tickets){
            ArrayList<ComentarioEntity> comentarios = cService.listarComentarios(ticket.getId());
            ticket.setComentarios(comentarios);
        }
      return tickets;
    }

    public ArrayList<TicketEntity> listarTickets(int colunaId) throws IOException {
        ArrayList<TicketEntity> tickets = dao.listarTickets(colunaId);
        ComentarioService cService = new ComentarioService();
        for(TicketEntity ticket : tickets){
            ArrayList<ComentarioEntity> comentarios = cService.listarComentarios(ticket.getId());
            ticket.setComentarios(comentarios);
        }
        return tickets;
    }

    public TicketEntity buscarTicket(int id) throws IOException {
        TicketEntity ticket = dao.buscarTicket(id);
        ComentarioService cService = new ComentarioService();
        ArrayList<ComentarioEntity> comentarios = cService.listarComentarios(id);
        ticket.setComentarios(comentarios);
        return ticket;
    }

    public boolean deletarTicket(int id) throws IOException {
        return dao.deletarTicket(id);
    }
    
    public int inserirTicket(TicketEntity ticket, int usuarioId) throws IOException {
        return dao.inserirTicket(ticket, usuarioId);
    }

    public TicketEntity atualizarTicket(TicketEntity ticket) throws IOException {
        return dao.atualizarTicket(ticket);
    }

    public void moverTicket(int colunaId, int ticketId) throws IOException {
        dao.moverTicket(colunaId, ticketId);
    }
}

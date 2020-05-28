package ads.kanban.controller;

import ads.kanban.model.entity.ColunaEntity;
import ads.kanban.model.entity.QuadroEntity;
import ads.kanban.model.entity.TicketEntity;
import ads.kanban.model.service.ColunaService;
import ads.kanban.model.service.QuadroService;
import ads.kanban.model.service.TicketService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Rotas {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public Rotas(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    protected void exibirQuadro(int idQuadro) throws ServletException, IOException {
        QuadroService qService = new QuadroService();
        ColunaService cService = new ColunaService();
        TicketService tService = new TicketService();

        QuadroEntity quadro = qService.buscarQuadro(idQuadro);
        ArrayList<ColunaEntity> colunas = cService.listarColunas(idQuadro);

        for (ColunaEntity item : colunas) {
            int colunaId = item.getId();
            ArrayList<TicketEntity>  tickets = tService.listarTickets(colunaId);
            item.setTickets(tickets);
        }

        request.setAttribute("quadro", quadro);
        request.setAttribute("colunas", colunas);
        RequestDispatcher view = request.getRequestDispatcher("/pages/quadro/ExibirQuadro.jsp");
        view.forward(request, response);
    }

    protected void home(int usuarioId) throws IOException, ServletException {
        QuadroService qService = new QuadroService();
        TicketService tService = new TicketService();
        request.setAttribute("tickets", tService.ultimosTickets(usuarioId, 3));
        request.setAttribute("quadros", qService.listarQuadros(usuarioId, 3));
        RequestDispatcher view = request.getRequestDispatcher("/pages/Home.jsp");
        view.forward(request, response);
    }
}
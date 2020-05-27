package ads.kanban.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ads.kanban.model.entity.ColunaEntity;
import ads.kanban.model.entity.ComentarioEntity;
import ads.kanban.model.entity.QuadroEntity;
import ads.kanban.model.entity.TicketEntity;
import ads.kanban.model.service.ColunaService;
import ads.kanban.model.service.ComentarioService;
import ads.kanban.model.service.QuadroService;
import ads.kanban.model.service.TicketService;
import sun.security.krb5.internal.Ticket;

@WebServlet("/ticket.do")
public class TicketController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String acao = request.getParameter("acao");
        String saida = "/index.jsp";

        int id = -1;

        TicketService tService = new TicketService();
        TicketEntity ticket = null;
        switch (acao) {
            case "btn-inserir":
                ticket = new TicketEntity();
                ticket.setTitulo(request.getParameter("titulo"));
                ticket.setDescricao(request.getParameter("descricao"));
                ticket.setFoto(request.getParameter("foto"));

                int colunaId = Integer.parseInt(request.getParameter("id_coluna"));
                ColunaService cService = new ColunaService();

                ticket.setColuna(cService.buscarColuna(colunaId));

                tService.inserirTicket(ticket);
                vaiPraQuadro(request, response);
                break;
        }

    }

    protected void vaiPraQuadro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id_quadro"));

        QuadroService qService = new QuadroService();
        ColunaService cService = new ColunaService();
        TicketService tService = new TicketService();

        QuadroEntity quadro = qService.buscarQuadro(id);
        ArrayList<ColunaEntity> colunas = cService.listarColunas(id);

        for (ColunaEntity item : colunas){
            int colunaId = item.getId();
            ArrayList<TicketEntity>  tickets = tService.listarTickets(colunaId);
            item.setTickets(tickets);
        }

        request.setAttribute("quadro", quadro);
        request.setAttribute("colunas", colunas);
        RequestDispatcher view = request.getRequestDispatcher("/pages/quadro/ExibirQuadro.jsp");
        view.forward(request, response);
    }
}

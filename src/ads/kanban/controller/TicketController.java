package ads.kanban.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ads.kanban.model.entity.*;
import ads.kanban.model.service.ColunaService;
import ads.kanban.model.service.TicketService;

@WebServlet("/ticket.do")
public class TicketController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String acao = request.getParameter("acao");

        int ticketId = -1;
        int colunaId = -1;

        //pega o usuario logado
        UsuarioLogado puxaUsuarioLogado = new UsuarioLogado(request);
        UsuarioEntity usuarioLogado = puxaUsuarioLogado.getUsuario();

        TicketService tService = new TicketService();
        ColunaService cService = new ColunaService();

        TicketEntity ticket = new TicketEntity();

        RenderHelper render = new RenderHelper(request,response);

        switch (acao) {
            case "btn-inserir":
                ticket.setTitulo(request.getParameter("titulo"));

                //pega id do usuário logado
                int usuarioId = usuarioLogado.getId();

                //pega id da coluna em que o ticket vai ser criado
                colunaId = Integer.parseInt(request.getParameter("id_coluna"));

                ticket.setColuna(cService.buscarColuna(colunaId));

                tService.inserirTicket(ticket,usuarioId);

                int quadroId = ticket.getColuna().getQuadro().getId();
                System.out.println(quadroId);
                render.exibirQuadro(quadroId);
                break;
            case "btn-excluir":
                ticketId = Integer.parseInt(request.getParameter("id_excluir"));
                ticket = tService.buscarTicket(ticketId);
                tService.deletarTicket(ticketId);

                //Cada ticket, possui uma coluna, e cada coluna, possui quadro
                //logo, reflitam, posso puxar de ticket, o id de quadro pra chamar
                //a renderização da página "ExibirQuadro.jsp"
                render.exibirQuadro(
                        ticket.getColuna().getQuadro().getId()
                );
                break;
            case "btn-mover":
                ticketId = Integer.parseInt(request.getParameter("id_ticket"));
                colunaId = Integer.parseInt(request.getParameter("id_coluna"));

                ticket = tService.buscarTicket(ticketId);
                tService.moverTicket(colunaId, ticketId);

                render.exibirQuadro(
                        ticket.getColuna().getQuadro().getId()
                );
                break;
            case "page-home":
                render.home(usuarioLogado.getId());
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


}

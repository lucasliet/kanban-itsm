package ads.kanban.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ads.kanban.model.entity.*;
import ads.kanban.model.service.ColunaService;
import ads.kanban.model.service.QuadroService;
import ads.kanban.model.service.TicketService;

@WebServlet("/ticket.do")
public class TicketController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String acao = request.getParameter("acao");
        String saida = "/index.jsp";

        //pega o usuario logado
        UsuarioLogado puxaUsuarioLogado = new UsuarioLogado(request);
        UsuarioEntity usuarioLogado = puxaUsuarioLogado.getUsuario();

        TicketService tService = new TicketService();
        TicketEntity ticket = null;

        Rotas rotas = new Rotas(request,response);

        switch (acao) {
            case "btn-inserir":
                ticket = new TicketEntity();
                ticket.setTitulo(request.getParameter("titulo"));

                //pega id do usuário logado
                int usuarioId = usuarioLogado.getId();

                //pega id da coluna que o ticket vai ser criado
                int colunaId = Integer.parseInt(request.getParameter("id_coluna"));
                ColunaService cService = new ColunaService();

                ticket.setColuna(cService.buscarColuna(colunaId));

                tService.inserirTicket(ticket,usuarioId);

                int quadroId = Integer.parseInt(request.getParameter("id_quadro"));
                rotas.exibirQuadro(quadroId);
                break;
            case "page-home":
                rotas.home(usuarioLogado.getId());
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


}

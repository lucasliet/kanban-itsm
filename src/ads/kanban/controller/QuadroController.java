package ads.kanban.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ads.kanban.model.entity.ColunaEntity;
import ads.kanban.model.entity.QuadroEntity;
import ads.kanban.model.entity.TicketEntity;
import ads.kanban.model.entity.UsuarioEntity;
import ads.kanban.model.service.ColunaService;
import ads.kanban.model.service.QuadroService;
import ads.kanban.model.service.TicketService;
import sun.security.krb5.internal.Ticket;


@WebServlet("/quadro.do")
public class QuadroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		String saida = "/index.jsp";
		
		int id;
		String titulo;
		
		QuadroEntity quadro = new QuadroEntity();
		ArrayList<QuadroEntity> quadros = new ArrayList<>();
		ArrayList<ColunaEntity> colunas = new ArrayList<>();
		ArrayList<TicketEntity> tickets = new ArrayList<>();
        QuadroService qService = new QuadroService();
		ColunaService cService = new ColunaService();
		TicketService tService = new TicketService();

		UsuarioLogado usuarioLogado;

        HttpSession session = request.getSession();
        UsuarioEntity usuario = null;
        Object aux = null;

        switch (acao) {
			case "btn-excluir":
				id = Integer.parseInt(request.getParameter("id_excluir"));
				qService.deletarQuadro(id);
            case "page-meus-quadros":
				usuarioLogado = new UsuarioLogado(request);
				usuario = usuarioLogado.getUsuario();

                quadros = qService.listarQuadros(usuario.getId());
                request.setAttribute("quadros", quadros);
                saida = "/pages/quadro/MeusQuadros.jsp";
                break;

            case "page-exibir":
            	id = Integer.parseInt(request.getParameter("id_exibir"));

				quadro = qService.buscarQuadro(id);
				colunas = cService.listarColunas(id);

				ArrayList<ColunaEntity> colunasTickets = new ArrayList<>();

				for (ColunaEntity item : colunas){
					int colunaId = item.getId();
					tickets = tService.listarTickets(colunaId);
					item.setTickets(tickets);

					colunasTickets.add(item);
					System.out.println(tickets);
					System.out.println(colunasTickets);
				}

            	request.setAttribute("quadro", quadro);
            	request.setAttribute("colunas", colunasTickets);
            	saida = "/pages/quadro/ExibirQuadro.jsp";
            	break;

            case "btn-atualizar":
    			id = Integer.parseInt(request.getParameter("id_atualizar"));
    			titulo = request.getParameter("titulo");
    			quadro.setId(id);
    			quadro.setTitulo(titulo);
    			QuadroEntity quadroNovo = qService.atualizarQuadro(quadro);
    			request.setAttribute("quadro", quadroNovo);
    			saida = "/pages/quadro/ExibirQuadro.jsp";
    			break;

			case "btn-inserir":
				titulo = request.getParameter("titulo");
				quadro = new QuadroEntity();
				quadro.setTitulo(titulo);

				usuarioLogado = new UsuarioLogado(request);
				usuario = usuarioLogado.getUsuario();

				id = qService.inserirQuadro(quadro, usuario);
				quadro.setId(id);
				request.setAttribute("quadro", quadro);
				saida = "/pages/quadro/ExibirQuadro.jsp";
				break;
		}
		RequestDispatcher view = request.getRequestDispatcher(saida);
		view.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}

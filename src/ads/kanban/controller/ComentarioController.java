package ads.kanban.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ads.kanban.model.entity.ComentarioEntity;
import ads.kanban.model.entity.TicketEntity;
import ads.kanban.model.entity.UsuarioEntity;
import ads.kanban.model.service.ComentarioService;
import ads.kanban.model.service.TicketService;

@WebServlet("/comentario.do")
public class ComentarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		String saida = null;

		UsuarioLogado puxaUsuarioLogado = new UsuarioLogado(request);
		UsuarioEntity usuarioLogado = puxaUsuarioLogado.getUsuario();

		ComentarioEntity comentario = new ComentarioEntity();
		ComentarioService cService = new ComentarioService();
		TicketService tService = new TicketService();
		RenderHelper render = new RenderHelper(request, response);


		switch (acao) {
			case "btn-postar":
				int idTicket = Integer.parseInt(request.getParameter("id_ticket"));
				TicketEntity ticket = tService.buscarTicket(idTicket);
				comentario.setCorpo(
						request.getParameter("corpo")
				);
				comentario.setTicket(ticket);
				comentario.setUsuario(usuarioLogado);
				cService.inserirComentario(comentario);
				render.exibirQuadro(ticket.getColuna().getQuadro().getId());
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
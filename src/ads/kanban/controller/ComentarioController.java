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

		UsuarioLogado puxaUsuarioLogado = new UsuarioLogado(request);
		UsuarioEntity usuarioLogado = puxaUsuarioLogado.getUsuario();

		ComentarioEntity comentario = new ComentarioEntity();
		ComentarioService cService = new ComentarioService();
		TicketService tService = new TicketService();
		RenderHelper render = new RenderHelper(request, response);

		int idTicket = Integer.parseInt(request.getParameter("id_ticket"));
		TicketEntity ticket = tService.buscarTicket(idTicket);
		switch (acao) {
			case "btn-postar":
				comentario.setCorpo(
						request.getParameter("corpo")
				);
				comentario.setTicket(ticket);
				comentario.setUsuario(usuarioLogado);
				cService.inserirComentario(comentario);
				break;
			case "curtir" :
				int idUsuario = usuarioLogado.getId();
				int idComentario = Integer.parseInt(request.getParameter("id_comentario"));
				cService.curtir(idUsuario,idComentario);
		}
		if(request.getParameter("page").equals("home")){
			render.home(usuarioLogado.getId());
		} else {
			render.exibirQuadro(ticket.getColuna().getQuadro().getId());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
package ads.kanban.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ads.kanban.model.entity.ComentarioEntity;
import ads.kanban.model.service.ComentarioService;
@WebServlet("/comentario.do")
public class ComentarioController {
	public class QuadroController extends HttpServlet {
		private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			String acao = request.getParameter("acao");
			String saida = null;
			
			int id;
			String corpo;
			
			
			ComentarioEntity comentario = new ComentarioEntity();
			ArrayList<ComentarioEntity> coment = new ArrayList<>();
			ComentarioService cService = new ComentarioService();
	        
			switch (acao) {
			case "page-excluir":
            	id = Integer.parseInt(request.getParameter("id_excluir"));
            	//TODO comentario = cService.buscarComentario(id);
            	cService.excluirComentario(id);
    			request.setAttribute("comentario", coment);
    		    saida = "AdmQuadro.jsp";
    			break;
			
			   
				case "btn-inserir":
	            	corpo = request.getParameter("corpo");
	            	comentario = new ComentarioEntity();
	            	comentario.setCorpo(corpo);
	            	id = cService.inserirComentario(comentario);
	            	request.setAttribute("comentario", comentario);
	            	saida = "ExibirQuadro.jsp";
	            	break;
			}
			RequestDispatcher view = request.getRequestDispatcher(saida);
			view.forward(request, response);
		}
	}
}
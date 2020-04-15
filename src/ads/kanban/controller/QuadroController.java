package ads.kanban.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ads.kanban.model.entity.QuadroEntity;
import ads.kanban.model.service.QuadroService;


@WebServlet("/quadro.do")
public class QuadroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		String saida = null;
		
		int id;
		String titulo;
		
		QuadroEntity quadro = new QuadroEntity();
		ArrayList<QuadroEntity> quadros = new ArrayList<>();
        QuadroService qService = new QuadroService();
        
		switch (acao) {
            case "page-quadro-adm":
                quadros = qService.listarQuadros();
                request.setAttribute("quadros", quadros);
                saida = "AdmQuadro.jsp";
                break;
            case "page-exibir":
            	id = Integer.parseInt(request.getParameter("id_exibir"));
            	quadro = qService.buscarQuadro(id);
            	request.setAttribute("quadro", quadro);
            	saida = "ExibirQuadro.jsp";
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

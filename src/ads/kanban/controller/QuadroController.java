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
                
            case "page-todos":
            	quadros = qService.listarQuadros();
            	request.setAttribute("quadros", quadros);
            	saida = "TodosQuadros.jsp";
            	break;
            	
            case "page-exibir":
            	id = Integer.parseInt(request.getParameter("id_exibir"));
            	quadro = qService.buscarQuadro(id);
            	request.setAttribute("quadro", quadro);
            	saida = "ExibirQuadro.jsp";
            	break;
            	
            case "page-atualizar":
            	id = Integer.parseInt(request.getParameter("id_atualizar"));
            	quadro = qService.buscarQuadro(id);
            	request.setAttribute("quadro", quadro);
            	saida = "AtualizarQuadro.jsp";
            	break;
            	
            case "page-excluir":
            	id = Integer.parseInt(request.getParameter("id_excluir"));
    			quadro = qService.buscarQuadro(id);
    			request.setAttribute("quadro", quadro);
    			request.setAttribute("btn", "<button type=\"button\" class=\"btn btn-danger text-uppercase\" data-toggle=\"modal\" data-target=\"#modalExcluir\">Excluir </button>");
    		    saida = "ExibirQuadro.jsp";
    			break;
            
            case "btn-inserir":
            	titulo = request.getParameter("titulo");
            	quadro = new QuadroEntity();
            	quadro.setTitulo(titulo);
            	id = qService.inserirQuadro(quadro);
            	request.setAttribute("quadro", quadro);
            	saida = "ExibirQuadro.jsp";
            	break;

            case "btn-atualizar":
    			id = Integer.parseInt(request.getParameter("id_atualizar"));
    			titulo = request.getParameter("titulo");
    			quadro.setId(id);
    			quadro.setTitulo(titulo);
    			QuadroEntity quadroNovo = qService.atualizarQuadro(quadro);
    			request.setAttribute("quadro", quadroNovo);
    			saida = "ExibirQuadro.jsp";
    			break;
    			
            case "btn-excluir":
    			id = Integer.parseInt(request.getParameter("id_excluir"));
    			quadro = qService.buscarQuadro(id);
    			qService.deletarQuadro(id);
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

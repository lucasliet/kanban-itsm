package ads.kanban.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ads.kanban.model.entity.ComentarioEntity;

public class ComentarioController {
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
			
			ComentarioEntity quadro = new ComentarioEntity();
			ArrayList<ComentarioEntity> quadros = new ArrayList<>();
			ComentarioEntity qService = new ComentarioEntity();
	        
			switch (acao) {
	            case "page-quadro-adm":
	                quadros = qService.listarQuadros();
	                request.setAttribute("quadros", quadros);
	                saida = "AdmQuadro.jsp";
	                break;
			}
		}

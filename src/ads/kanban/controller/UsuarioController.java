package ads.kanban.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ads.kanban.model.entity.UsuarioEntity;
import ads.kanban.model.service.UsuarioService;

@WebServlet("/usuario.do")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		
		int id = 0;
		String nome = null, ultimoNome = null, endereco = null, telefone = null, email = null, senha = null, saida = null;
		
		UsuarioEntity usuario = new UsuarioEntity();
		UsuarioService uService = new UsuarioService();
		ArrayList<UsuarioEntity> usuarios = new ArrayList<>();
		
		switch (acao) {
            case "btn-cadastrar":
            	nome = request.getParameter("nome");
            	ultimoNome = request.getParameter("sobrenome");
            	email = nome.toLowerCase()+ultimoNome.toLowerCase()+"@cte.com";
            	senha = request.getParameter("senha");
            	endereco = request.getParameter("endereco");
            	telefone = request.getParameter("telefone");
            	
            	usuario.setNome(nome);
            	usuario.setUltimoNome(ultimoNome);
            	usuario.setEmail(email);
            	usuario.setSenha(senha);
            	usuario.setEndereco(endereco);
            	usuario.setTelefone(telefone);
            	
            	id = uService.inserirUsuario(usuario);
            	usuario.setId(id);
            	
            	saida = "index.jsp";
            	break;
            case "page-home": //Login
            	email = request.getParameter("email");
            	senha = request.getParameter("senha");
            	
            	usuarios = uService.listarUsuarios();
            	
            	for (UsuarioEntity item : usuarios) {
            		if (item.getEmail().equals(email) && item.getSenha().equals(senha)) {
            			System.out.println(item);
            			saida = "ExibirQuadro.jsp"; 
            			//TODO mudar pra pagina home real
            			break;
            		} else {
                    	String nlogou = "<div class=\"bg-light rounded shadow p-2 m-2\"><p>Usuário não encontrado</p></div>";
                    	request.setAttribute("msgerror", nlogou);
                    	saida = "index.jsp";
            		}
            	}
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

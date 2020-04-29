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
		
		String nome = null, ultimoNome = null, endereco = null, telefone = null, email = null, senha = null, saida = null;
		UsuarioEntity usuario;
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
            	
            	usuario = new UsuarioEntity();
            	usuario.setNome(nome);
            	usuario.setUltimoNome(ultimoNome);
            	usuario.setEmail(email);
            	usuario.setSenha(senha);
            	usuario.setEndereco(endereco);
            	usuario.setTelefone(telefone);
				usuario.setFoto("img/fotoPadrao.png");
            	
            	uService.inserirUsuario(usuario);
            	
            	saida = "index.jsp";
            	break;
//			case "btn-atualizar":
//				usuarios = uService.listarUsuarios();
//				String senhaAntiga = request.getParameter("senha_antiga");
//
//				for (UsuarioEntity item : usuarios) {
//					if (item.getSenha().equals(senhaAntiga)){
//						nome = request.getParameter("nome");
//						ultimoNome = request.getParameter("sobrenome");
//						senha = request.getParameter("senha_nova");
//						endereco = request.getParameter("endereco");
//						telefone = request.getParameter("telefone");
//
//						usuario = new UsuarioEntity();
//						usuario.setNome(nome);
//						usuario.setUltimoNome(ultimoNome);
//                        usuario.setEndereco(endereco);
//                        usuario.setTelefone(telefone);
//                        usuario.setEmail(item.getEmail());
//						usuario.setSenha(senha);
//						usuario.setFoto("img/fotoPadrao.png"); //TODO substituir foto padrão pelo que o usuário enviou
//						uService.atualizarUsuario(usuario);
//					}
//				}
//				saida = "EditarPerfil.jsp";
//				break;
            case "page-home": //Login
            	email = request.getParameter("email");
            	senha = request.getParameter("senha");
            	
            	usuarios = uService.listarUsuarios();
            	
            	for (UsuarioEntity item : usuarios) {
            		if (item.getEmail().equals(email) && item.getSenha().equals(senha)) {
            			request.setAttribute("usuario",item);
            			saida = "EditarPerfil.jsp";
            			//TODO mudar pra pagina home real
            			break;
            		} else {
                    	String nlogou = "<div class=\"bg-danger text-white rounded shadow p-2 m-2\"><p>Usuário não encontrado</p></div>";
                    	request.setAttribute("msgerror", nlogou);
                    	saida = "index.jsp";
            		}
            	}
            	break;
            case "page-perfil":
            	usuario = uService.buscarUsuario(1);
            	request.setAttribute("usuario", usuario); //TODO salvar o usuário logado pra exibir aqui
            	saida = "editarPerfil.jsp";
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

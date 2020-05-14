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

import ads.kanban.model.entity.UsuarioEntity;
import ads.kanban.model.service.UsuarioService;

@WebServlet("/usuario.do")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		
		String saida = "index.jsp";
		UsuarioEntity usuario = null;
		UsuarioService uService = new UsuarioService();
		ArrayList<UsuarioEntity> usuarios = new ArrayList<>();
		
        HttpSession session = request.getSession();
		
		switch (acao) {
            case "btn-cadastrar": //Cadastrar novo usuário
            	//passando nome e sobrenome em variáveis só pra 
            	//facilitar fazer o e-mail automaticamente a partir deles depois
            	String nome = request.getParameter("nome");
            	String ultimoNome = request.getParameter("sobrenome");

            	
            	usuario = new UsuarioEntity();
            	usuario.setNome(nome);
            	usuario.setUltimoNome(ultimoNome);
            	usuario.setEmail(nome.toLowerCase()+ultimoNome.toLowerCase()+"@cte.com");
            	usuario.setSenha(request.getParameter("senha"));
            	usuario.setEndereco(request.getParameter("endereco"));
            	usuario.setTelefone(request.getParameter("telefone"));
				usuario.setFoto("img/fotoPadrao.png");
            	
            	uService.inserirUsuario(usuario);
            	
            	saida = "index.jsp";
            	break;
			case "btn-atualizar": //Atualizar Perfil
				usuario = new UsuarioEntity();

				Object aux = session.getAttribute("usuario");
				if (aux != null && aux instanceof UsuarioEntity) {
					usuario = (UsuarioEntity)aux;
				}
				
				usuario.setNome(request.getParameter("nome"));
				usuario.setUltimoNome(request.getParameter("sobrenome"));
                usuario.setEndereco(request.getParameter("endereco"));
                usuario.setTelefone(request.getParameter("telefone"));
				usuario.setSenha(request.getParameter("senha_nova"));
				usuario.setFoto("img/fotoPadrao.png"); //TODO substituir foto padrão pelo que o usuário enviou
				uService.atualizarUsuario(usuario);
				
				session.setAttribute("usuario", usuario);
				saida = "EditarPerfil.jsp";
				break;
            case "page-home": //Login
            	String email = request.getParameter("email");
            	String senha = request.getParameter("senha");
            	
            	usuarios = uService.listarUsuarios();
            	
            	//checa na lista de todos usuários qual tem email e senha iguais ao digitado 
            	//(não é a forma mais performatica)
            	for (UsuarioEntity item : usuarios) {
            		if (item.getEmail().equals(email) && item.getSenha().equals(senha)) {
            			//manda esse objeto usuário direto pra sessão, inves de mandar pro request
            			//ai pode ser reaproveitado em outras páginas
            			session.setAttribute("usuario",item);
            			saida = "Home.jsp";
            			break;
            		} else {
            			//Caso não encontre nenhum usuario com esse e-mail e senha
            			//Cria um card com aviso de "usuário não encontrado" e manda pro index.jsp
                    	String nlogou = "<div class=\"bg-danger text-white rounded shadow p-2 m-2\"><p>Usuário não encontrado</p></div>";
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

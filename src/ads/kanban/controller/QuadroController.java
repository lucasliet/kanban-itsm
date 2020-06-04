package ads.kanban.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ads.kanban.model.entity.QuadroEntity;
import ads.kanban.model.entity.UsuarioEntity;
import ads.kanban.model.service.QuadroService;


@WebServlet("/quadro.do")
public class QuadroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");

		int idQuadro , idUsuario;
		String titulo;
		
		QuadroEntity quadro = new QuadroEntity();
        QuadroService qService = new QuadroService();

        UsuarioLogado puxaUsuarioLogado = new UsuarioLogado(request);
		UsuarioEntity usuarioLogado = puxaUsuarioLogado.getUsuario();

		RenderHelper render = new RenderHelper(request, response);

        switch (acao) {
			case "btn-excluir":
				idQuadro = Integer.parseInt(request.getParameter("id_excluir"));
				qService.deletarQuadro(idQuadro);
            case "page-meus-quadros":
                render.meusQuadros(usuarioLogado.getId());
                break;

            case "page-exibir":
            	idQuadro = Integer.parseInt(request.getParameter("id_exibir"));
            	render.exibirQuadro(idQuadro);
            	break;

            case "btn-atualizar":
    			idQuadro = Integer.parseInt(request.getParameter("id_quadro"));
    			titulo = request.getParameter("titulo");
    			quadro.setId(idQuadro);
    			quadro.setTitulo(titulo);
    			qService.atualizarQuadro(quadro);
    			render.exibirQuadro(idQuadro);
    			break;

			case "btn-inserir":
				titulo = request.getParameter("titulo");
				quadro.setTitulo(titulo);

				//Insere o quadro no banco, pega o ID que o banco gerou e retorna na variável ID
				idQuadro = qService.inserirQuadro(quadro, usuarioLogado);
				render.exibirQuadro(idQuadro);
				break;
			case "+usuario-quadro":
				idQuadro = Integer.parseInt(request.getParameter("id_quadro"));
				idUsuario = Integer.parseInt(request.getParameter("id_usuario"));

				qService.inserirUsuarioNoQuadro(idQuadro, idUsuario);
				render.exibirQuadro(idQuadro);
				break;
			case "-usuario-quadro":
				idQuadro = Integer.parseInt(request.getParameter("id_quadro"));
				idUsuario = Integer.parseInt(request.getParameter("id_usuario"));

				qService.removerUsuarioNoQuadro(idQuadro, idUsuario);
				render.exibirQuadro(idQuadro);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}

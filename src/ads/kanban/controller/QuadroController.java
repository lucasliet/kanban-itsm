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

		int id;
		String titulo;
		
		QuadroEntity quadro = new QuadroEntity();
        QuadroService qService = new QuadroService();

        UsuarioLogado puxaUsuarioLogado = new UsuarioLogado(request);
		UsuarioEntity usuarioLogado = puxaUsuarioLogado.getUsuario();

		RenderHelper render = new RenderHelper(request, response);

        switch (acao) {
			case "btn-excluir":
				id = Integer.parseInt(request.getParameter("id_excluir"));
				qService.deletarQuadro(id);
            case "page-meus-quadros":
                render.meusQuadros(usuarioLogado.getId());
                break;

            case "page-exibir":
            	id = Integer.parseInt(request.getParameter("id_exibir"));
            	render.exibirQuadro(id);
            	break;

            case "btn-atualizar":
    			id = Integer.parseInt(request.getParameter("id_atualizar"));
    			titulo = request.getParameter("titulo");
    			quadro.setId(id);
    			quadro.setTitulo(titulo);
    			qService.atualizarQuadro(quadro);
    			render.exibirQuadro(id);
    			break;

			case "btn-inserir":
				titulo = request.getParameter("titulo");
				quadro.setTitulo(titulo);

				//Insere o quadro no banco, pega o ID que o banco gerou e retorna na variável ID
				id = qService.inserirQuadro(quadro, usuarioLogado);
				render.exibirQuadro(id);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}

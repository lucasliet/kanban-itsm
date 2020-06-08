package ads.kanban.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ads.kanban.model.entity.UsuarioEntity;

@WebFilter("/*")
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		//Converte request e response por treta do filter
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;

		UsuarioLogado puxaUsuarioLogado = new UsuarioLogado(req);
		UsuarioEntity usuarioLogado = puxaUsuarioLogado.getUsuario();

		//pega o endereço de link
		String path = req.getContextPath();
		String uri = req.getRequestURI();
		String acao = req.getParameter("acao");
		if (acao == null) {
			acao = "";
		}

		if (usuarioLogado != null ||
				uri.equals(path+"/") ||
				acao.equals("login") ||
				acao.equals("btn-cadastrar") ||
				uri.endsWith("pages/usuario/Cadastro.jsp") ||
				uri.endsWith("img/logoEmpresa.png") ||
				uri.endsWith("img/favicon.ico") ||
				uri.endsWith(".svg") ||
				uri.endsWith(".js") ||
				uri.endsWith(".css")) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(path+"/");
		}
	}
}

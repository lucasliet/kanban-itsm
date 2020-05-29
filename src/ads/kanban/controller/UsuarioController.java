package ads.kanban.controller;

import java.io.IOException;

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

        String saida = "/index.jsp";
        UsuarioEntity usuario = null;
        UsuarioService uService = new UsuarioService();

        HttpSession session = request.getSession();

        //puxa o usuário logado
        UsuarioLogado puxaUsuarioLogado = new UsuarioLogado(request);
        UsuarioEntity usuarioLogado = puxaUsuarioLogado.getUsuario();

        RenderHelper render = new RenderHelper(request, response);
        switch (acao) {
            case "btn-cadastrar": //Cadastrar novo usuário
                //passando nome e sobrenome em variáveis só pra
                //facilitar fazer o e-mail automaticamente a partir deles depois
                String nome = request.getParameter("nome");
                String ultimoNome = request.getParameter("sobrenome");

                usuario = new UsuarioEntity();
                usuario.setNome(nome);
                usuario.setUltimoNome(ultimoNome);
                usuario.setEmail(
                        nome.toLowerCase().replaceAll("\\s+","") +  //esse replaceAll remove todos os espaços da string
                        ultimoNome.toLowerCase().replaceAll("\\s+","") +
                        "@cte.com"
                );
                usuario.setSenha(request.getParameter("senha"));
                usuario.setEndereco(request.getParameter("endereco"));
                usuario.setTelefone(request.getParameter("telefone"));
                usuario.setFoto("/img/fotoPadrao.png");

                uService.inserirUsuario(usuario);
                break;
            case "btn-atualizar": //Atualizar Perfil
                //checa se tem algum usuário logado na sessão
                //se tiver, coloca dentro do objeto 'usuario'
                usuario = usuarioLogado;
                // Checa se a senha que o usuário digitou é igual a senha do banco
                if (request.getParameter("senha_antiga").equals(usuario.getSenha())) {
                    //Se for igual, atualiza os dados
                    usuario.setNome(request.getParameter("nome"));
                    usuario.setUltimoNome(request.getParameter("sobrenome"));
                    usuario.setEndereco(request.getParameter("endereco"));
                    usuario.setTelefone(request.getParameter("telefone"));
                    usuario.setSenha(request.getParameter("senha_nova"));
                    usuario.setFoto("/img/fotoPadrao.png"); //TODO substituir foto padrão pelo que o usuário enviou
                    uService.atualizarUsuario(usuario);
                    //troca o usuário da sessão pro usuário com os dados novos
                    session.setAttribute("usuario", usuario);
                    //mensagem de feedback que o perfil foi atualizado com sucesso
                    render.editarPerfil(true);
                } else {
                    //Se a senha estiver errada, só retorna mensagem de erro
                    render.editarPerfil(false);
                }
                saida = "/pages/usuario/EditarPerfil.jsp";
                break;
            case "login":
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");

                //Checa se existe algum usuário no BD com esse e-mail e senha
                usuario = uService.login(email, senha);

                //Caso ache o usuário, ele tera id maior que 1, já que não existe id 0 no banco
                // ai ele manda pra sessão e loga
                if (usuario.getId() != 0) {
                    session.setAttribute("usuario", usuario);

                    render.home(usuario.getId());
                    saida = "/pages/Home.jsp";
                } else {
                    //Caso não encontre nenhum usuario com esse e-mail e senha, ele terá id = 0
                    //Então ele manda de volta pra index com mensagem de erro
                    render.loginError();
                }
                break;
            case "logout": session.invalidate();
        }
        RequestDispatcher view = request.getRequestDispatcher(saida);
		view.forward(request,response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

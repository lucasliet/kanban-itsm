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
import ads.kanban.model.service.QuadroService;
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
                usuario = new UsuarioEntity();
                //Mensagem de feedback se o usuário foi atualizado ou não
                //Ela vai ser alimentada nos ifs
                String authFeedback = "";

                //checa se tem algum usuário logado na sessão
                //se tiver, coloca dentro do objeto 'usuario'
                Object aux = session.getAttribute("usuario");
                if (aux != null && aux instanceof UsuarioEntity) {
                    usuario = (UsuarioEntity) aux;
                }
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
                    authFeedback = "<div class='bg-success text-white rounded shadow px-3 py-2 m-3'>" +
                                         "<i class='fas fa-check'></i>&nbsp Perfil Atualizado" +
                                   "</div>";
                } else {
                    //Se a senha estiver errada, só retorna mensagem de erro
                    authFeedback = "<div class='bg-danger text-white rounded shadow px-3 py-2 m-3'>" +
                                         "<i class='fas fa-times'></i>&nbsp Senha Incorreta" +
                                   "</div>";
                }

                request.setAttribute("authFeedback", authFeedback);
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
                    //Puxa a lista de quadros pra por na timeline da home
                    QuadroService qService = new QuadroService();
                    request.setAttribute("quadros", qService.listarQuadros(usuario.getId(), 3));

                    saida = "/pages/Home.jsp";
                } else {
                    //Caso não encontre nenhum usuario com esse e-mail e senha, ele terá id = 0
                    //Cria um card com aviso de "usuário não encontrado" e manda pro index.jsp
                    String nlogou = "<div class='bg-danger text-white rounded px-3 py-2 my-2'>" +
                                        "<i class='fas fa-times'></i>&nbsp Usuario não encontrado" +
                                    "</div>";

                    request.setAttribute("msgerror", nlogou);
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

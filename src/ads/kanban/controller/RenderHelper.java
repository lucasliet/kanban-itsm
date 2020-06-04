package ads.kanban.controller;

import ads.kanban.model.entity.ColunaEntity;
import ads.kanban.model.entity.ComentarioEntity;
import ads.kanban.model.entity.QuadroEntity;
import ads.kanban.model.entity.UsuarioEntity;
import ads.kanban.model.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class RenderHelper {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final QuadroService qService = new QuadroService();
    private final UsuarioService uService = new UsuarioService();
    private final ColunaService cService = new ColunaService();
    private final TicketService tService = new TicketService();
    private final ComentarioService coService = new ComentarioService();
    private final UsuarioLogado puxaUsuarioLogado;
    private final UsuarioEntity usuarioLogado;

    public RenderHelper(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.puxaUsuarioLogado = new UsuarioLogado(request);
        this.usuarioLogado = puxaUsuarioLogado.getUsuario();
    }

    private void chamaJSP(String page) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher(page);
        view.forward(request, response);
    }

    protected void loginError() throws  IOException, ServletException {
        //Cria um card com aviso de "usuário não encontrado" e manda pro index.jsp
        String nlogou = "<div class='bg-danger text-white rounded px-3 py-2 my-2'>" +
                            "<i class='fas fa-times'></i>&nbsp Usuario não encontrado" +
                        "</div>";

        request.setAttribute("msgerror", nlogou);
        chamaJSP("/index.jsp");
    }

    protected void home(int usuarioId) throws IOException, ServletException {
        request.setAttribute("tickets", tService.ultimosTickets(usuarioId, 3));
        request.setAttribute("quadros", qService.ultimosQuadros(usuarioId, 3));
        chamaJSP("/pages/Home.jsp");
    }

    protected void meusQuadros(int usuarioId) throws IOException, ServletException {
        ArrayList<QuadroEntity> quadros = qService.listarQuadros(usuarioId);
        request.setAttribute("quadros", quadros);
        chamaJSP("/pages/quadro/MeusQuadros.jsp");
    }

    protected void exibirQuadro(int quadroId) throws ServletException, IOException {
        ArrayList<UsuarioEntity> usuarios = uService.listarUsuarios();

        ArrayList<ColunaEntity> colunas = cService.listarColunas(quadroId);
        //Faz a lista de options com cada coluna pra mandar pro JSP
        //Seria o mesmo que o forEach em taglib, mas ele ta dando defeito
        String optionsColunas = "";
        for (ColunaEntity item : colunas) {
            optionsColunas+= "<option value=\""+item.getId()+"\">"+item.getTitulo()+"</option>\n";
        }

        String optionsUsuarios = "";
        for (UsuarioEntity item : usuarios) {
            //Só escreve o option pros usuários que forem diferentes do logado
            if(item.getId() != usuarioLogado.getId()){
                optionsUsuarios+= "<option value=\""+item.getId()+"\">"+item.getNome()+" "+item.getUltimoNome()+"</option>\n";
            }
        }
        request.setAttribute("options_colunas", optionsColunas);
        request.setAttribute("options_usuarios", optionsUsuarios);

        request.setAttribute("quadro", qService.buscarQuadro(quadroId));
        request.setAttribute("colunas", colunas);
        chamaJSP("/pages/quadro/ExibirQuadro.jsp");
    }

    protected void editarPerfil(boolean sucesso) throws IOException, ServletException {
        String feedbackMsg;
        if (sucesso) {
            feedbackMsg = "<div class='bg-success text-white rounded shadow px-3 py-2 m-3'>" +
                                "<i class='fas fa-check'></i>&nbsp Perfil Atualizado" +
                          "</div>";
        } else {
            feedbackMsg = "<div class='bg-danger text-white rounded shadow px-3 py-2 m-3'>" +
                                "<i class='fas fa-times'></i>&nbsp Senha Incorreta" +
                          "</div>";
        }
        request.setAttribute("authFeedback", feedbackMsg);
        chamaJSP("/pages/usuario/EditarPerfil.jsp");
    }
}
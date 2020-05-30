package ads.kanban.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ads.kanban.model.entity.ColunaEntity;
import ads.kanban.model.service.ColunaService;
import ads.kanban.model.service.QuadroService;

@WebServlet("/coluna.do")
public class ColunaController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String acao = request.getParameter("acao");

        ColunaEntity coluna = null;
        ColunaService cService = new ColunaService();
        QuadroService qService = new QuadroService();
        int id = -1;

        ArrayList<ColunaEntity> colunas = null;

        RenderHelper render = new RenderHelper(request,response);

        switch (acao) {
            case "btn-inserir":
                coluna = new ColunaEntity();
                coluna.setTitulo(request.getParameter("titulo"));
                coluna.setQuadro(
                        qService.buscarQuadro(
                                Integer.parseInt(request.getParameter("id_quadro"))
                        )
                );
                cService.inserirColuna(coluna);
                render.exibirQuadro(coluna.getQuadro().getId());
                break;
            case "btn-excluir":
                id = Integer.parseInt(request.getParameter("id_excluir"));
                coluna = cService.buscarColuna(id);
                cService.deletarColuna(id);
                render.exibirQuadro(coluna.getQuadro().getId());
                break;
            case "btn-editar":
                id = Integer.parseInt(request.getParameter("id_editar"));
                String titulo = request.getParameter("titulo_coluna");
                coluna = cService.buscarColuna(id);
                coluna.setTitulo(titulo);
                cService.atualizarColuna(coluna);
                render.exibirQuadro(coluna.getQuadro().getId());
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

package ads.kanban.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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

        String saida = "/index.jsp";
        ColunaEntity coluna = null;
        ColunaService cService = new ColunaService();
        QuadroService qService = new QuadroService();
        int id = -1;

        ArrayList<ColunaEntity> colunas = null;

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
                colunas = cService.listarColunas(coluna.getQuadro().getId());

                request.setAttribute("quadro", coluna.getQuadro());
                request.setAttribute("colunas",colunas);
                saida = "/pages/quadro/ExibirQuadro.jsp";
                break;
        }
        RequestDispatcher view = request.getRequestDispatcher(saida);
        view.forward(request,response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

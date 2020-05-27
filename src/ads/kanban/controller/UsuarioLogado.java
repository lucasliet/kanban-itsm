package ads.kanban.controller;

import ads.kanban.model.entity.UsuarioEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UsuarioLogado {
    private UsuarioEntity usuario;

    public UsuarioLogado(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object aux = session.getAttribute("usuario");
        if (aux != null && aux instanceof UsuarioEntity) {
            UsuarioEntity usuario = (UsuarioEntity) aux;
            this.usuario = usuario;
        }
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }
}

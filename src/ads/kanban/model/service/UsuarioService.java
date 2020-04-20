package ads.kanban.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.kanban.model.dao.UsuarioDAO;
import ads.kanban.model.entity.UsuarioEntity;

public class UsuarioService {
	public UsuarioEntity buscarUsuario(int id) throws IOException {
		UsuarioDAO dao = new UsuarioDAO();
		return dao.buscarUsuario(id);
	}
	
	public int inserirUsuario(UsuarioEntity usuario) throws IOException {
		UsuarioDAO dao = new UsuarioDAO();
		return dao.inserirUsuario(usuario);
	}
	
	public int deletarUsuario(int id) throws IOException {
		UsuarioDAO dao = new UsuarioDAO();
		return dao.deletarUsuario(id);
	}
	
	public UsuarioEntity atualizarUsuario(UsuarioEntity usuario) throws IOException {
		UsuarioDAO dao = new UsuarioDAO();
		return dao.atualizarUsuario(usuario);
	}
	
	public ArrayList<UsuarioEntity> listarUsuarios() throws IOException{
		UsuarioDAO dao = new UsuarioDAO();
		return dao.listarUsuarios();
	}
}

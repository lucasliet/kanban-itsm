package ads.kanban.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.kanban.model.dao.UsuarioDAO;
import ads.kanban.model.entity.UsuarioEntity;

public class UsuarioService {
	private	UsuarioDAO dao;
	public UsuarioService() {
		this.dao = new UsuarioDAO();
	}

	public UsuarioEntity login(String email, String senha) throws IOException {
		return dao.login(email, senha);
	}

	public UsuarioEntity buscarUsuario(int id) throws IOException {
		return dao.buscarUsuario(id);
	}
	
	public int inserirUsuario(UsuarioEntity usuario) throws IOException {
		return dao.inserirUsuario(usuario);
	}
	
	public int deletarUsuario(int id) throws IOException {
		return dao.deletarUsuario(id);
	}
	
	public UsuarioEntity atualizarUsuario(UsuarioEntity usuario) throws IOException {
		return dao.atualizarUsuario(usuario);
	}
	
	public ArrayList<UsuarioEntity> listarUsuarios() throws IOException{
		return dao.listarUsuarios();
	}
}

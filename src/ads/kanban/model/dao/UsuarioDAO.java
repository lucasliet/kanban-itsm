package ads.kanban.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.kanban.model.entity.UsuarioEntity;

public class UsuarioDAO {
	public UsuarioEntity buscarUsuario(int id) throws IOException {
		UsuarioEntity usuario = new UsuarioEntity();
		String sql = "SELECT nome, ultimo_nome, endereco, telefone, email FROM usuarios WHERE id = ?";
		
		try (Connection conn = ConnectionFactory.getConnection();
	             PreparedStatement pst = conn.prepareStatement(sql);){

	            pst.setInt(1, id);
	            try (ResultSet rs = pst.executeQuery();) {

	                while (rs.next()) {
	                    usuario.setId(id);
	                    usuario.setNome(rs.getString("nome"));
	                    usuario.setUltimoNome(rs.getString("ultimo_nome"));
	                    usuario.setEndereco(rs.getString("endereco"));
	                    usuario.setTelefone(rs.getString("telefone"));
	                    usuario.setEmail(rs.getString("email"));
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	                throw new IOException(e);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new IOException(e);
	        }
		return usuario;
	}
	
	public int inserirUsuario(UsuarioEntity usuario) throws IOException {
        int id = -1;
        String sql = "INSERT INTO usuarios (nome, ultimo_nome, endereco, telefone, email, senha ) VALUES (?,?,?,?,?,?)";

        try (Connection conn = ConnectionFactory.getConnection(); 
        		PreparedStatement pst = conn.prepareStatement(sql);) {

            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getUltimoNome());
            pst.setString(3, usuario.getEndereco());
            pst.setString(4, usuario.getTelefone());
            pst.setString(5, usuario.getEmail());
            pst.setString(6, usuario.getSenha());
            pst.execute();

            // obter o id criado
            String query = "SELECT LAST_INSERT_ID()";
            try (PreparedStatement pst1 = conn.prepareStatement(query); 
            		ResultSet rs = pst1.executeQuery();) {

                if (rs.next()) {
                    id = rs.getInt(1);
                    usuario.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        System.out.println("Usuario: " + usuario.getNome() + " de ID " + usuario.getId() + " gerado no banco com sucesso");
        return id;
    }
	
	public int deletarUsuario(int id) throws IOException {
        int feedback = -1;
        UsuarioEntity usuario = new UsuarioEntity();
        String sql = "DELETE FROM usuarios WHERE id = ?";
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);){
                pst.setInt(1, id);
                pst.execute();
                feedback = 1;
        }catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        if (feedback == -1){
            System.out.println("Operação falhou");
        }else{
            System.out.println("Usuario: " + usuario.getNome() + " de ID " + usuario.getId() + " foi removido com sucesso");
        }
        return feedback;
    }

	public UsuarioEntity atualizarUsuario(UsuarioEntity usuario) throws IOException {
        String sql = "UPDATE usuarios SET nome = ?, ultimo_nome = ?, endereco = ?, telefone = ?, email = ?, senha = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection(); 
        		PreparedStatement pst = conn.prepareStatement(sql);) {
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getUltimoNome());
            pst.setString(3, usuario.getEndereco());
            pst.setString(4, usuario.getTelefone());
            pst.setString(5, usuario.getEmail());
            pst.setString(6, usuario.getSenha());
            pst.setInt(7, usuario.getId());
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
            UsuarioEntity usuarioAlterado = buscarUsuario(usuario.getId());
        return usuarioAlterado;
    }
	
	 public ArrayList<UsuarioEntity> listarUsuarios() throws IOException {
			String sql ="SELECT id, nome, ultimo_nome, endereco, telefone, email  FROM quadros ORDER BY nome";
			ArrayList<UsuarioEntity> usuarios = new ArrayList<>();
			
			try (Connection conn = ConnectionFactory.getConnection(); 
					PreparedStatement pst = conn.prepareStatement(sql)){
				try (ResultSet rs = pst.executeQuery();){
					
					while(rs.next()) {
						UsuarioEntity usuario= new UsuarioEntity();
						usuario.setId(rs.getInt("id"));
						usuario.setNome(rs.getString("nome"));
						usuario.setUltimoNome(rs.getString("ultimo_nome"));
						usuario.setEndereco(rs.getString("endereco"));;
						usuario.setTelefone(rs.getString("telefone"));
						usuario.setEmail(rs.getString("email"));
						usuarios.add(usuario);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
	    	
	    	return usuarios;
	    }
}

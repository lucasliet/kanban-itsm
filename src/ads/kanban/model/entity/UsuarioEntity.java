package ads.kanban.model.entity;

public class UsuarioEntity {
	private int id;
	private String nome;
	private String ultimoNome;
	private String endereco;
	private String telefone;
	private String email;
	private String senha;
	private String foto;

	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUltimoNome() {
		return ultimoNome;
	}
	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Override
	public String toString() {
		return "Usuário: [id=" + id + ", nome=" + nome + ", ultimoNome=" + ultimoNome + ", endereco=" + endereco
				+ ", telefone=" + telefone + ", email=" + email + ", senha=" + senha + "]";
	}
	
}

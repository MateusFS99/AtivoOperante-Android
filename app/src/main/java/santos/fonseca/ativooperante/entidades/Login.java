package santos.fonseca.ativooperante.entidades;

public class Login {

	private int id;
	private String cpf, email, senha;
	
	public Login() {
		this("","","");
	}
	
	public Login(int id, String cpf, String email, String senha) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
	}
	
	public Login(String cpf, String email, String senha) {
		super();
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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
}
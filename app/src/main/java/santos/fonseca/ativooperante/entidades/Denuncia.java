package santos.fonseca.ativooperante.entidades;

public class Denuncia {

	private int id, urgencia;
	private String titulo, descricao;
	private Orgao orgao;
	private Tipo tipo;
	private Login login;
	
	public Denuncia() {
		this(0,"","",null,null,null);
	}

	public Denuncia(int id, int urgencia, String titulo, String descricao, Orgao orgao, Tipo tipo, Login login) {
		super();
		this.id = id;
		this.urgencia = urgencia;
		this.titulo = titulo;
		this.descricao = descricao;
		this.orgao = orgao;
		this.tipo = tipo;
		this.login = login;
	}

	public Denuncia(int urgencia, String titulo, String descricao, Orgao orgao, Tipo tipo, Login login) {
		super();
		this.urgencia = urgencia;
		this.titulo = titulo;
		this.descricao = descricao;
		this.orgao = orgao;
		this.tipo = tipo;
		this.login = login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUrgencia() {
		return urgencia;
	}

	public void setUrgencia(int urgencia) {
		this.urgencia = urgencia;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
}
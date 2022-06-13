package santos.fonseca.ativooperante.entidades;

public class Tipo {

	private int id;
	private String nome, nivel;
	
	public Tipo() {
		this("","");
	}
	
	public Tipo(int id, String nome, String nivel) {
		super();
		this.id = id;
		this.nome = nome;
		this.nivel = nivel;
	}
	
	public Tipo(String nome, String nivel) {
		super();
		this.nome = nome;
		this.nivel = nivel;
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

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
}
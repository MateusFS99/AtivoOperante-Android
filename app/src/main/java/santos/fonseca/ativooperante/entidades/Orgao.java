package santos.fonseca.ativooperante.entidades;

public class Orgao {

	private int id;
	private String nome;
	
	public Orgao() {
		this("");
	}
	
	public Orgao(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Orgao(String nome) {
		super();
		this.nome = nome;
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
}
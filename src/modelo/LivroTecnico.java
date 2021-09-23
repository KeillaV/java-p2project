package modelo;
public class LivroTecnico extends Livro {
	private String assunto;
	
	public LivroTecnico(String titulo, String resumo, String idioma, String editora, String genero, int ano, int unidades, float preco, String assunto) {
		super(titulo, resumo, idioma, editora, genero, ano, unidades, preco);
		setTipo("Técnico");
		this.assunto = assunto;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}	
}

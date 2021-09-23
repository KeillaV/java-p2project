package modelo;
public class LivroPeriodico extends Livro {
	private int mesDeLancamento;
	private int numeroDaEdicao;

	public LivroPeriodico(String titulo, String resumo, String idioma, String editora, String genero, int ano, int unidades, float preco, int mesDeLancamento,
			int numeroDaEdicao) {
		super(titulo, resumo, idioma, editora, genero, ano, unidades, preco);
		setTipo("Periódico");
		this.mesDeLancamento = mesDeLancamento;
		this.numeroDaEdicao = numeroDaEdicao;
	}

	public int getMesDeLancamento() {
		return mesDeLancamento;
	}

	public void setMesDeLancamento(int mesDeLancamento) {
		this.mesDeLancamento = mesDeLancamento;
	}

	public int getNumeroDaEdicao() {
		return numeroDaEdicao;
	}

	public void setNumeroDaEdicao(int numeroDaEdicao) {
		this.numeroDaEdicao = numeroDaEdicao;
	}

}

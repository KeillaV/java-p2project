package modelo;

import java.util.HashMap;

public class Colecao {
	private HashMap<Livro, String> livrosDaColecao;
	
	public Colecao() {
		this.livrosDaColecao = new HashMap<>();
	}
	
	public void adicionarLivro(Livro livro) {
		this.livrosDaColecao.put(livro, "Sem avaliação");
	}
	
	public void adicionarAvaliacao(Livro livro, String avaliacao) {
		this.livrosDaColecao.put(livro, avaliacao);
	}

	public HashMap<Livro, String> getLivrosDaColecao() {
		return this.livrosDaColecao;
	}

	public void setAvaliacoesLivro(HashMap<Livro, String> livrosDaColecao) {
		this.livrosDaColecao = livrosDaColecao;
	}
}

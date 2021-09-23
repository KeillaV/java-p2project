package modelo;

import java.util.ArrayList;

public class LivroLiteratura extends Livro {
	private ArrayList<String> autores = new ArrayList<String>();

	public LivroLiteratura(String titulo, String resumo, String idioma, String editora, String genero, int ano, int unidades, float preco, ArrayList<String> autores) {
		super(titulo, resumo, idioma, editora, genero, ano, unidades, preco);
		setTipo("Literatura");
		this.autores = autores;
	}

	public ArrayList<String> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<String> autores) {
		this.autores = autores;
	}

}

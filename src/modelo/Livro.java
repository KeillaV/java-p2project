package modelo;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Livro {
	private long id;
	private String titulo;
	private String resumo;
	private String idioma;
	private String genero;
	private String editora;
	private int anoDePublicacao;
	private String tipo;
	private int unidades;
	private float preco;
	private int numeroDeVisualizacoes;
	private HashSet<String> emailsDosClientesInteressados;
	private ArrayList<Comentario> listaDeComentarios;
	
	public Livro(String titulo, String resumo, String idioma, String editora, String genero, int ano, int unidades, float preco) {
		this.id = System.currentTimeMillis();
		this.titulo = titulo;
		this.resumo = resumo;
		this.idioma = idioma;
		this.editora = editora;
		this.genero = genero;
		this.anoDePublicacao = ano;
		this.unidades = unidades;
		this.preco = preco;
		
		this.listaDeComentarios = new ArrayList<>();
		this.emailsDosClientesInteressados = new HashSet<>();
	}
	
	public String toString() {
		return this.titulo + ", " + this.anoDePublicacao;
	}
	
	public void adicionarComentario(Comentario comentario) {
		this.listaDeComentarios.add(comentario);
	}
	
	public Comentario recuperarComentario(long id) {
		Comentario comentarioSelecionado = null;
		for (Comentario comentario: this.listaDeComentarios) {
			if (comentario.getId() == id) {
				comentarioSelecionado = comentario;
			}
		}
	
		return comentarioSelecionado;
	}
	
	public void removerComentario(Comentario comentario) {
		this.listaDeComentarios.remove(comentario);
	}

	public long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public int getAnoDePublicacao() {
		return anoDePublicacao;
	}

	public void setAnoDePublicacao(int ano) {
		this.anoDePublicacao = ano;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public float getPreco() {
		return preco;
	}
	
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	public int getUnidades() {
		return unidades;
	}
	
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	public int getNumeroDeVisualizacoes() {
		return numeroDeVisualizacoes;
	}
	
	public void setNumeroDeVisualizacoes(int numeroDeVisualizacoes) {
		this.numeroDeVisualizacoes = numeroDeVisualizacoes;
	}
	
	public ArrayList<Comentario> getListaDeComentarios() {
		return listaDeComentarios;
	}
	
	public void setListaDeComentarios(ArrayList<Comentario> listaDeComentarios) {
		this.listaDeComentarios = listaDeComentarios;
	}

	public HashSet<String> getEmailsDosClientesInteressados() {
		return emailsDosClientesInteressados;
	}

	public void setEmailsDosClientesInteressados(HashSet<String> emailsDosClientesInteressados) {
		this.emailsDosClientesInteressados = emailsDosClientesInteressados;
	}
}

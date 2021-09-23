package modelo;

import java.util.Date;

public class Comentario {
	private Usuario autor;
	private long id;
	private Date data;
	private String mensagem;
	
	public Comentario(Usuario autor, String mensagem) {
		this.autor = autor;
		this.mensagem = mensagem;
		this.setId(System.currentTimeMillis());
		data = new Date();
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}

package modelo;


import java.util.ArrayList;
import java.util.HashSet;

public class CentralDeInformacoes {
	private static CentralDeInformacoes instancia;
	private ArrayList<Livro> todosOsLivros;
	private HashSet<Usuario> todosOsUsuarios;
	
	
	public CentralDeInformacoes() {
		this.todosOsLivros = new ArrayList<>();
		this.todosOsUsuarios = new HashSet<>();
	}
	
	public static CentralDeInformacoes getInstance() {
		if (instancia == null) {
			Persistencia persistencia = new Persistencia();
			instancia = persistencia.recuperarCentral("central.xml");
		}
		
		return instancia;
	}
	
	public Livro recuperarLivro(long id) {
		Livro livroSelecionado = null;
		for (Livro livro: this.todosOsLivros) {
			if (livro.getId() == id) {
				livroSelecionado = livro;
			}
		}
	
		return livroSelecionado;
	}
	
	public boolean adicionarLivro(Livro livro) {
		if (recuperarLivro(livro.getId()) == null) {
			this.todosOsLivros.add(livro);
			Persistencia persistencia = new Persistencia();
			persistencia.salvarCentral(this, "central.xml");
			
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<Livro> getTodosOsLivros() {
		return this.todosOsLivros;
	}
	
	public void setTodosOsLivros(ArrayList<Livro> todosOsLivros) {
		this.todosOsLivros = todosOsLivros;
	}
	
	public Usuario recuperarUsuario(String email) throws Exception {
		Usuario usuarioEscolhido = null; 
		for (Usuario usuario: this.todosOsUsuarios) {
			if (usuario.getEmail().equals(email)) {
				usuarioEscolhido = usuario;
			}
		}
		
		if (usuarioEscolhido == null) {
			throw new Exception("Usuário não cadastrado!");
		}
		
		return usuarioEscolhido;
	}
	
	public Usuario recuperarUsuario(String email, String senha) throws Exception {
		Usuario usuarioEscolhido = null; 
		for (Usuario usuario: this.todosOsUsuarios) {
			if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
				usuarioEscolhido = usuario;
			}
		}
		
		if (usuarioEscolhido == null) {
			throw new Exception("Usuário não cadastrado!");
		}
		
		return usuarioEscolhido;
	}
	
	public void adicionarUsuario(Usuario usuario) throws Exception {
		for (Usuario usuarioCadastrado: this.todosOsUsuarios) {
			if (usuarioCadastrado.getEmail().equals(usuario.getEmail())) {
				throw new Exception("O email informado já está cadastrado!");
			}
		}
		
		this.todosOsUsuarios.add(usuario);
		Persistencia persistencia = new Persistencia();
		persistencia.salvarCentral(this, "central.xml");
	}

	public HashSet<Usuario> getTodosOsUsuarios() {
		return todosOsUsuarios;
	}

	public void setTodosOsUsuarios(HashSet<Usuario> todosOsUsuarios) {
		this.todosOsUsuarios = todosOsUsuarios;
	}	
}

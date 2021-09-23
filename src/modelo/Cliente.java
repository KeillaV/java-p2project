package modelo;


public class Cliente extends Usuario {
	private String dataDeNascimento;
	private String sexo;
	private Object[] generosFavoritos;
	private Colecao colecaoDeLivros;
	
	public Cliente(String nome, String email, String senha, String dataDeNascimento, String sexo, Object[] generosFavoritos) {
		this.setNome(nome);
		this.setEmail(email);
		this.setSenha(senha);
		this.setDataDeNascimento(dataDeNascimento);
		this.setSexo(sexo);
		this.setGenerosFavoritos(generosFavoritos);
		this.setColecaoDeLivros(new Colecao());
	}
	
	public String getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Object[] getGenerosFavoritos() {
		return generosFavoritos;
	}
	public void setGenerosFavoritos(Object[] generosFavoritos) {
		this.generosFavoritos = generosFavoritos;
	}

	public Colecao getColecaoDeLivros() {
		return colecaoDeLivros;
	}

	public void setColecaoDeLivros(Colecao colecaoDeLivros) {
		this.colecaoDeLivros = colecaoDeLivros;
	}
}

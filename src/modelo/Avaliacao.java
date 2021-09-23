package modelo;

public enum Avaliacao {
	MUITO_BOM("Muito bom"),
	BOM("Bom"),
	MEDIANO("Mediano"),
	RUIM("Ruim"),
	MUITO_RUIM("Muito ruim");
	
	private String nota;
	Avaliacao(String nota) {
		this.nota = nota;
	}
	
	public String getNota () {
		return this.nota;
	}
}

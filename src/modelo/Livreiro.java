package modelo;

public class Livreiro extends Usuario {
	private static Livreiro livreiro;
	// Faltam atributos 
	
	private Livreiro() {
		
	}
	
	public static Livreiro getInstance() {
		if (livreiro == null) {
			livreiro = new Livreiro();
		}
		
		return livreiro;
	}
}

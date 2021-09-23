package modelo;

public class GeradorDeSenhas {
	private static char[] CARACTERES_SENHA = {'1', '2', '3', '4', '5', '6', '7', '8', '9',
			'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h',
			'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'P', 'p',
			'Q', 'q', 'R', 'r', 'S', 's', 'T', 't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x',
			'Y', 'y', 'Z', 'z'};
	
	public static String gerarSenhaAleatória() {
		StringBuilder senha = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			int indice = (int) (Math.random() * CARACTERES_SENHA.length);
			char caractere = CARACTERES_SENHA[indice];
			
			senha.append(caractere);
		}
		
		return senha.toString();
	}
}

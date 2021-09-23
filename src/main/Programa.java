package main;

import javax.swing.JOptionPane;

import janelas.JanelaCadastroLivreiro;
import janelas.JanelaLogin;
import modelo.CentralDeInformacoes;

public class Programa {

	public static void main(String[] args) {
		CentralDeInformacoes central = CentralDeInformacoes.getInstance();
		
		if (central.getTodosOsLivros().size() == 0) {
			JOptionPane.showMessageDialog(null, "Bem-vindo livreiro! Hora de fazer seu cadastro", "Cadastro do livreiro", JOptionPane.PLAIN_MESSAGE, null);
			JanelaCadastroLivreiro janelaLivreiro = new JanelaCadastroLivreiro();
		} else {
			JanelaLogin janelaLogin = new JanelaLogin();
		}
	}
}

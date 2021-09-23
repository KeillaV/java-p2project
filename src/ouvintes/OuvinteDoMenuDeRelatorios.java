package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.Mensageiro;
import modelo.atributos.estaticos.AtributosProjeto;

public class OuvinteDoMenuDeRelatorios implements ActionListener {

	private JFrame janela;
	
	public OuvinteDoMenuDeRelatorios(JFrame janela) {
		this.janela = janela;
	}
	
	public void actionPerformed(ActionEvent e) {
		String opcao = e.getActionCommand();
		
		switch (opcao) {
			case "Gerar relatório dos livros cadastrados":
				String email = AtributosProjeto.USUARIO_LOGADO.getEmail();
				Mensageiro.enviarListaDeLivros(email);
				
				JOptionPane.showMessageDialog(janela, "O relatório foi enviado para o seu email!", "Relatório dos livros cadastrados", JOptionPane.PLAIN_MESSAGE, null);
				break;
		}
		
	}

}

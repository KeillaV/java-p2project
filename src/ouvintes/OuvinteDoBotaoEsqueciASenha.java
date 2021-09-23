package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.CentralDeInformacoes;
import modelo.GeradorDeSenhas;
import modelo.Mensageiro;
import modelo.Persistencia;
import modelo.Usuario;
import modelo.atributos.estaticos.AtributosProjeto;

public class OuvinteDoBotaoEsqueciASenha implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		String email = JOptionPane.showInputDialog(null, "Digite o seu email para redefinir sua senha: ");
		
		if (email != null) {
			try {
				CentralDeInformacoes central = CentralDeInformacoes.getInstance();
				Usuario usuario = central.recuperarUsuario(email);
				String novaSenha = GeradorDeSenhas.gerarSenhaAleatória();
				
				JOptionPane.showMessageDialog(null, "Um email foi enviado com uma senha provisória!", "Redefinição da senha", JOptionPane.WARNING_MESSAGE, null);
				Mensageiro.enviarSenhaProvisoria(email, novaSenha);
				
				usuario.setSenha(novaSenha);
				usuario.setRedefinindoSenha(true);
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);;
			}	
		}	
	}
}

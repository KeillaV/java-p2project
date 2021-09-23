package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import janelas.JanelaComListaDeLivros;
import janelas.JanelaLogin;
import modelo.CentralDeInformacoes;
import modelo.Livreiro;
import modelo.Persistencia;
import modelo.Usuario;
import modelo.atributos.estaticos.AtributosProjeto;

public class OuvinteDoBotaoLogin implements ActionListener {

	private JanelaLogin janela;
	
	public OuvinteDoBotaoLogin (JanelaLogin janela) {
		this.janela = janela;
	}
	
	public void actionPerformed(ActionEvent e) {
		String email = janela.getJEmail().getText();
		String senha = new String(janela.getJSenha().getPassword());
			
		if (email.isEmpty() || senha.isEmpty()) {
			JOptionPane.showMessageDialog(janela, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE, null);
			
		} else {
			try {
				CentralDeInformacoes central = CentralDeInformacoes.getInstance();
				
				Usuario usuario = central.recuperarUsuario(email, senha);
				AtributosProjeto.USUARIO_LOGADO = usuario;
				
				if (usuario.isRedefinindoSenha()) { 
					String novaSenha = JOptionPane.showInputDialog(janela, "Digite sua nova senha:", "Redefinição de senha", JOptionPane.PLAIN_MESSAGE);
					
					if (!novaSenha.isEmpty() && novaSenha != null) {
						usuario.setSenha(novaSenha);
						usuario.setRedefinindoSenha(false);
						
						Persistencia persistencia = new Persistencia();
						persistencia.salvarCentral(central, "central.xml");
						
					} else {
						JOptionPane.showMessageDialog(janela, "Não foi possível redefinir a senha!", "Erro", JOptionPane.ERROR_MESSAGE, null);
					}
					
				}

				JOptionPane.showMessageDialog(janela, "Bem-vindo(a) " + usuario.getNome() + "!", "Login", JOptionPane.PLAIN_MESSAGE, null);
				janela.dispose();
					
				new JanelaComListaDeLivros(AtributosProjeto.USUARIO_LOGADO);
				
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(janela, e2.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
			}
		}	
	}
}

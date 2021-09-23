package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import janelas.JanelaCadastroLivreiro;
import janelas.JanelaLogin;
import modelo.CentralDeInformacoes;
import modelo.Livreiro;

public class OuvinteDoBotaoCadastrarLivreiro implements ActionListener {
	private JanelaCadastroLivreiro janela;
	
	public OuvinteDoBotaoCadastrarLivreiro(JanelaCadastroLivreiro janela) {
		this.janela = janela;	
	}
	
	public void actionPerformed(ActionEvent botao) {
		String nome = janela.getTfNome().getText();
		String email = janela.getTfEmail().getText();
		String senha = new String (janela.getPfSenha().getPassword());
		
		if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
			JOptionPane.showMessageDialog(janela, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE, null);
		} else {
			Livreiro usuario = Livreiro.getInstance();
			usuario.setNome(nome);
			usuario.setEmail(email);
			usuario.setSenha(senha);
			
			try {
				CentralDeInformacoes central = CentralDeInformacoes.getInstance();
				central.adicionarUsuario(usuario);
				JOptionPane.showMessageDialog(janela, "Livreiro cadastrado com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
				janela.dispose();
				new JanelaLogin();
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(janela, e1.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE, null);
			}
			
		}
	}
}


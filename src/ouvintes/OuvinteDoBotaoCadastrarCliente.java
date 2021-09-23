package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import janelas.JanelaCadastroCliente;
import janelas.JanelaLogin;
import modelo.CentralDeInformacoes;
import modelo.Cliente;
import modelo.Persistencia;

public class OuvinteDoBotaoCadastrarCliente implements ActionListener {
	
	private JanelaCadastroCliente janela;
	
	public OuvinteDoBotaoCadastrarCliente(JanelaCadastroCliente janela) {
		this.janela = janela;
	}
	
	public void actionPerformed(ActionEvent e) {
		CentralDeInformacoes central = CentralDeInformacoes.getInstance();
		
		String nome = janela.getTfNome().getText();
		String email = janela.getTfEmail().getText();
		String senha = new String (janela.getPfSenha().getPassword());
		String dataDeNascimento = janela.getFtDataDeNascimento().getText();
		String sexo = (String) janela.getCbSexos().getSelectedItem();
		Object[] generosFavoritos = janela.getListaGeneros().getSelectedValuesList().toArray();
		
		if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || dataDeNascimento.isEmpty()) {
			JOptionPane.showMessageDialog(janela, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE, null);
			
		} else if (generosFavoritos.length > 3) {
			JOptionPane.showMessageDialog(janela, "Por favor, escolha apenas três gêneros no máximo!", "Aviso", JOptionPane.WARNING_MESSAGE, null);
			
		} else {
			Cliente usuario = new Cliente(nome, email, senha, dataDeNascimento, sexo, generosFavoritos);
	
			try {
				central.adicionarUsuario(usuario);
				JOptionPane.showMessageDialog(janela, "Usuário cadastrado com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
				janela.dispose();
				new JanelaLogin();
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(janela, e1.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE, null);
			}
		}
	}

}

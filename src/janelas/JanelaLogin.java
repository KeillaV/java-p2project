package janelas;

import java.awt.Font;

import javax.swing.*;

import ouvintes.OuvinteDoBotaoEsqueciASenha;
import ouvintes.OuvinteDoBotaoLogin;


public class JanelaLogin extends JanelaComMenu {
	private JButton botao;
	private JButton esqueciASenha;
	private JTextField jEmail;
	private JPasswordField jSenha;
	private Font fonte = new Font("Arial", Font.BOLD, 14);
	
	public JanelaLogin() {
		setTitle("Login");
		setSize(500,300);
		setLocationRelativeTo(null);
		adicionarJlabels();
		adicionarTextFields();
		adicionarBotoes();
		setVisible(true);
	}
	
	private void adicionarJlabels() {
		JLabel email = new JLabel("E-mail:");
		email.setBounds(70, 20, 80 , 30);
		email.setFont(fonte);
		email.setOpaque(true);
		add(email);
		JLabel senha = new JLabel("Senha:");
		senha.setBounds(70, 90, 80 , 30);
		senha.setFont(fonte);
		senha.setOpaque(true);
		add(senha);
	}
	
	private void adicionarTextFields() {
		jEmail = new JTextField();
		jEmail.setBounds(70, 50, 350, 30);
		add(jEmail);
		jSenha = new JPasswordField();
		jSenha.setBounds(70, 120, 350, 30);

		add(jSenha);
	}
	
	private void adicionarBotoes() {
		JButton botao = new JButton("Login");
		botao.setFont(fonte);
		botao.setBounds(100, 175, 110, 40);
		add(botao);
		
		OuvinteDoBotaoLogin ouvinte = new OuvinteDoBotaoLogin(this);
		botao.addActionListener(ouvinte);
		
		JButton esqueciASenha = new JButton("Esqueci a senha");
		esqueciASenha.setFont(fonte);
		esqueciASenha.setBounds(230, 175, 160, 40);
		add(esqueciASenha);
		 
		OuvinteDoBotaoEsqueciASenha ouvi = new OuvinteDoBotaoEsqueciASenha();
		esqueciASenha.addActionListener(ouvi);
	}
	
	public JButton getBotao() {
		return botao;
	}
	
	public JButton getEsqueciASenha() {
		return esqueciASenha;
	}
	
	public JTextField getJEmail() {
		return jEmail;
	}
	
	public JPasswordField getJSenha() {
		return jSenha;

	}
}

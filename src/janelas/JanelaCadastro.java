package janelas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public abstract class JanelaCadastro extends JanelaComMenu {

	private Font fonte = new Font("Arial", Font.PLAIN, 14);
	private JButton btCadastrar;
	private JTextField tfNome;
	private JTextField tfEmail;
	private JPasswordField pfSenha;
	
	public JanelaCadastro() {
		setTitle("Cadastro");
		setSize(600,350);
		setLocationRelativeTo(null);
		adicionarLabels();
		adicionarTextFields();
		adicionarBotao();
		adicionarOuvinteDoBotaoCadastrar();
	}

	public abstract void adicionarOuvinteDoBotaoCadastrar();

	private void adicionarLabels() {
		JLabel lbNome = new JLabel("Nome completo:");
		lbNome.setBounds(30, 20, 110, 30);
		lbNome.setFont(fonte);
		add(lbNome);
		
		JLabel lbEmail = new JLabel("Email:");
		lbEmail.setBounds(30, 70, 100, 30);
		lbEmail.setFont(fonte);
		lbEmail.setHorizontalAlignment(JLabel.CENTER);
		add(lbEmail);
		
		JLabel lbSenha = new JLabel("Senha:");
		lbSenha.setBounds(30, 120, 100, 30);
		lbSenha.setFont(fonte);
		lbSenha.setHorizontalAlignment(JLabel.CENTER);
		add(lbSenha);
		
	}

	private void adicionarTextFields() {
		tfNome = new JTextField();
		tfNome.setBounds(145, 20, 400, 30);
		tfNome.setFont(fonte);
		add(tfNome);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(145, 70, 400, 30);
		tfEmail.setFont(fonte);
		add(tfEmail);
		
		pfSenha = new JPasswordField();
		pfSenha.setBounds(145, 120, 200, 30);
		add(pfSenha);
		
	}

	private void adicionarBotao() {
		btCadastrar = new JButton("Cadastrar");
		btCadastrar.setBounds(395, 220, 150, 40);
		btCadastrar.setFont(new Font("Arial", Font.BOLD, 14));
		
		add(btCadastrar);
	}

	public JButton getBtCadastrar() {
		return btCadastrar;
	}

	public JTextField getTfNome() {
		return tfNome;
	}

	public JTextField getTfEmail() {
		return tfEmail;
	}

	public JPasswordField getPfSenha() {
		return pfSenha;
	}

	public Font getFonte() {
		return fonte;
	}
}

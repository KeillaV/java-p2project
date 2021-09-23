package janelas;

import javax.swing.*;

import modelo.Livro;
import modelo.LivroTecnico;

import java.awt.Font;

public class CadastroLivroTecnico extends JanelaCadastroLivro {
	private JTextField jfAssunto;
	private JComboBox<String> generosTecnicos;

	public CadastroLivroTecnico() {
		super();
		setTitle("Cadastro dos Livros Técnicos");
		adicionarCombo();
		adicionarTextField();
		adicionarLabels();
		setVisible(true);
	}
	
	public CadastroLivroTecnico(Livro livro) {
		super(livro);
		adicionarTextField(livro);
		adicionarLabels();
		setVisible(true);
	}

	private void adicionarLabels() {
		JLabel lassunto = new JLabel("Assunto:");
		lassunto.setBounds(30,360, 180, 40);
	    lassunto.setFont(new Font("Arial", Font.BOLD,14));
		add(lassunto);
		
	}

	private void adicionarTextField() {
		jfAssunto = new JTextField();
		jfAssunto.setBounds(30, 390, 250, 30);
		add(jfAssunto);
		
	}
	
	private void adicionarTextField(Livro livro) {
		LivroTecnico livroT = (LivroTecnico) livro;
		
		jfAssunto = new JTextField(livroT.getAssunto());
		jfAssunto.setBounds(30, 390, 250, 30);
		add(jfAssunto);
	}

	private void adicionarCombo() {
		String[] generos= {"Paradidático", "Formação Profissional"};
		generosTecnicos= new JComboBox<String>(generos);
		generosTecnicos.setBounds(350, 330, 250, 30);
		add(generosTecnicos);	
		
	}

	public JTextField getJfAssunto() {
		return jfAssunto;
	}

	public JComboBox<String> getGenerosTecnicos() {
		return generosTecnicos;
	}

}




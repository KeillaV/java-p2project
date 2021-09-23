package janelas;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;

import modelo.Livro;
import modelo.LivroDesenvolvimentoPessoal;
import modelo.LivroLiteratura;

public class CadastroLivroDesenvolvimentoPessoal extends JanelaCadastroLivro {
	private JTextArea jtAutores;
	private JComboBox<String> generosDesenvolvimento;

	public CadastroLivroDesenvolvimentoPessoal() {
		super();
		setTitle("Cadastro dos Livros de Desenvolvimento Pessoal");
		adicionarCombo();
		adicionarTextField();
		adicionarLabels();
		setVisible(true);
		
	}
	
	public CadastroLivroDesenvolvimentoPessoal(Livro livro) {
		super(livro);
		adicionarTextField(livro);
		adicionarLabels();
		setVisible(true);
		
	}

	private void adicionarLabels() {
		JLabel lAutores = new JLabel("Autor(es):");
		lAutores.setBounds(30, 360, 180, 40);
		lAutores.setFont(new Font("Arial", Font.BOLD,14));
		add(lAutores);
		
	}

	private void adicionarTextField() {
		jtAutores = new JTextArea();
		jtAutores.setLineWrap(true);
		jtAutores.setWrapStyleWord(true);
		jtAutores.setBounds(30,390, 250, 80);
		add(jtAutores);
		
	}
	
	private void adicionarTextField(Livro livro) {
		LivroDesenvolvimentoPessoal livroD = (LivroDesenvolvimentoPessoal) livro;
		
		ArrayList<String> autores = livroD.getAutores();
		StringBuilder stringAutores = new StringBuilder();
		
		for (int i = 0; i < autores.size(); i++) {
			stringAutores.append(autores.get(i));
			
			if (i == (autores.size() - 1)) {
				stringAutores.append(".");
			} else {
				stringAutores.append(", ");
			}
		}
		
		jtAutores = new JTextArea(stringAutores.toString());
		jtAutores.setLineWrap(true);
		jtAutores.setWrapStyleWord(true);
		jtAutores.setBounds(30,390, 250, 80);
		add(jtAutores);
	}
	

	private void adicionarCombo() {
		String[] generos= {"Autoajuda", "Religião", "Saúde"};
		generosDesenvolvimento = new JComboBox<String>(generos);
		generosDesenvolvimento.setBounds(350, 330, 250, 30);
		add(generosDesenvolvimento);	
		
	}
	

	public JTextArea getJtAutores() {
		return jtAutores;
	}

	public JComboBox<String> getGenerosDesenvolvimento() {
		return generosDesenvolvimento;
	}

	

}


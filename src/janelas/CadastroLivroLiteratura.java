package janelas;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;

import modelo.Livro;
import modelo.LivroLiteratura;

public class CadastroLivroLiteratura extends JanelaCadastroLivro {
	private JTextArea jaAutores;
	private JComboBox<String> generosLiteratura;
	
	public CadastroLivroLiteratura() {
		super();
		setTitle("Cadastro dos Livros de Literatura");
		adicionarCombo();
		adicionarJLabels();
		adicionarTextFields();
		setVisible(true);
	}
	
	public CadastroLivroLiteratura(Livro livro) {
		super(livro);
		adicionarJLabels();
		adicionarTextFields(livro);
		setVisible(true);
	}
	
	private void adicionarCombo() {
		String[] generos= {"Literatura Brasileira", "Literatura Estrangeira", "Infanto Juvenil", "Artes", "Biografias", "Poesia"};
		generosLiteratura = new JComboBox<String>(generos);
		generosLiteratura.setBounds(350, 330, 250, 30);
		add(generosLiteratura);
	}

	private void adicionarJLabels() {
		JLabel lAutores = new JLabel("Autor(es):");
		lAutores.setBounds(30, 360, 180, 40);
		lAutores.setFont(new Font("Arial", Font.BOLD,14));
		add(lAutores);
	}
	
	private void adicionarTextFields() {
		jaAutores = new JTextArea();
		jaAutores.setLineWrap(true);
		jaAutores.setWrapStyleWord(true);
		jaAutores.setBounds(30,390, 250, 80);
		add(jaAutores);
	}
	
	private void adicionarTextFields(Livro livro) {
		LivroLiteratura livroL = (LivroLiteratura) livro;
		
		ArrayList<String> autores = livroL.getAutores();
		StringBuilder stringAutores = new StringBuilder();
		
		for (int i = 0; i < autores.size(); i++) {
			stringAutores.append(autores.get(i));
			
			if (i == (autores.size() - 1)) {
				stringAutores.append(".");
			} else {
				stringAutores.append(", ");
			}
		}
		
		jaAutores = new JTextArea(stringAutores.toString());
		jaAutores.setLineWrap(true);
		jaAutores.setWrapStyleWord(true);
		jaAutores.setBounds(30,390, 250, 80);
		add(jaAutores);
	}
	
	public JTextArea getJaAutores() {
		return jaAutores;
	}


	public JComboBox<String> getGenerosLiteratura() {
		return generosLiteratura;
	}
}


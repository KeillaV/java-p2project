package janelas;

import java.awt.Font;

import javax.swing.*;

import modelo.Livro;
import modelo.LivroPeriodico;

public class CadastroLivroPeriodico extends JanelaCadastroLivro {
	private JTextField jfMesDeLancamento;
	private JTextField jfNumeroDaEdicao;
	private JComboBox<String> generosPeriodicos;

	public CadastroLivroPeriodico() {
		super();
		setTitle("Cadastro dos Livros Periódicos");
		adicionarCombo();
		adicionarLabels();
		adicionarFields();
		setVisible(true);
	}
	
	public CadastroLivroPeriodico(Livro livro) {
		super(livro);
		adicionarLabels();
		adicionarFields(livro);
		setVisible(true);
	}
	
	private void adicionarCombo() {
		String[] generos= {"Gibi", "Revista de Notícias"};
		generosPeriodicos = new JComboBox<String>(generos);
		generosPeriodicos.setBounds(350, 330, 250, 30);
		add(generosPeriodicos);
	}

	private void adicionarLabels() {
		JLabel lMesDeLancamento = new JLabel("Mês De Lançamento:");
		lMesDeLancamento.setBounds(30, 360, 180, 40);
		lMesDeLancamento.setFont(new Font("Arial", Font.BOLD,14));
		add(lMesDeLancamento);
		
		JLabel lNumeroDaEdicao = new JLabel("Número Da Edicão:");
		lNumeroDaEdicao.setBounds(30, 420, 180, 40);
		lNumeroDaEdicao.setFont(new Font("Arial", Font.BOLD,14));
		add(lNumeroDaEdicao);
		
	}
	
	private void adicionarFields() {
		jfMesDeLancamento = new JTextField();
		jfMesDeLancamento.setBounds(30,390, 250, 30);
		add(jfMesDeLancamento);
		
		jfNumeroDaEdicao = new JTextField();
		jfNumeroDaEdicao.setBounds(30, 450, 250, 30);
		add(jfNumeroDaEdicao);
	}
	
	private void adicionarFields(Livro livro) {
		LivroPeriodico livroP = (LivroPeriodico) livro;
		
		jfMesDeLancamento = new JTextField(String.valueOf(livroP.getMesDeLancamento()));
		jfMesDeLancamento.setBounds(30,390, 250, 30);
		add(jfMesDeLancamento);
		
		jfNumeroDaEdicao = new JTextField(String.valueOf(livroP.getNumeroDaEdicao()));
		jfNumeroDaEdicao.setBounds(30, 450, 250, 30);
		add(jfNumeroDaEdicao);
	}

	public JTextField getJfMesDeLancamento() {
		return jfMesDeLancamento;
	}

	public JComboBox<String> getGenerosPeriodicos() {
		return generosPeriodicos;
	}

	public JTextField getJfNumeroDaEdicao() {
		return jfNumeroDaEdicao;
	}	
}

package janelas;

import javax.swing.*;

import modelo.Livro;
import ouvintes.OuvinteDoBotaoCadastrarLivro;
import ouvintes.OuvinteDoBotaoSalvarEdicaoLivro;

import java.awt.Font;

public abstract class JanelaCadastroLivro extends JFrame {
	private JTextField jfTitulo;
	private JTextArea jaResumo;
	private JTextField jfIdioma;
	private JTextField jfEditora;
	private JTextField jfAnoDePublicacao;
	private JTextField jfUnidades;
	private JTextField jfPreco;	
	private JButton botao;

	public JanelaCadastroLivro() {
		setSize(700,550);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		adicionarJLabels();
		adicionarTextFields();
		adicionarJButton();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JanelaCadastroLivro(Livro livro) {
		setSize(700,550);
		setTitle("Edição de livro");
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		adicionarJLabels();
		adicionarTextFields(livro);
		adicionarJButton(livro);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void adicionarJLabels() {
		JLabel lTitulo = new JLabel("Titulo:");
		lTitulo.setBounds(30, 15, 180, 40);
		lTitulo.setFont(new Font("Arial", Font.BOLD,14));
		add(lTitulo);
		
		JLabel lIdioma = new JLabel("Idioma:");
		lIdioma.setBounds(30, 75, 180, 40);
	    lIdioma.setFont(new Font("Arial", Font.BOLD,14));
		add(lIdioma);
		
		JLabel lEditora = new JLabel("Editora:");
		lEditora.setBounds(30, 130, 180, 40);
	    lEditora.setFont(new Font("Arial", Font.BOLD,14));
		add(lEditora);
		
		JLabel lAno = new JLabel("Ano De Publicação:");
		lAno.setBounds(30, 190, 180, 40);
	    lAno.setFont(new Font("Arial", Font.BOLD,14));
		add(lAno);
		
		JLabel lUnidades = new JLabel("Unidades Disponíveis:");
		lUnidades.setBounds(30, 245, 180, 40);
	    lUnidades.setFont(new Font("Arial", Font.BOLD,14));
		add(lUnidades);
		
		JLabel lPrecos = new JLabel("Preço:");
		lPrecos.setBounds(30, 300, 180, 40);
	    lPrecos.setFont(new Font("Arial", Font.BOLD,14));
		add(lPrecos);
		
		JLabel lResumo = new JLabel("Resumo:");
		lResumo.setBounds(350, 10, 180, 40);
	    lResumo.setFont(new Font("Arial", Font.BOLD,14));
		add(lResumo);
		
	}
	
	private void adicionarTextFields() {
		jfTitulo = new JTextField();
		jfTitulo.setBounds(30, 45, 250, 30);
		add(jfTitulo);
		
		jfIdioma = new JTextField();
		jfIdioma.setBounds(30, 105, 250, 30);
		add(jfIdioma);
		
		jfEditora = new JTextField();
		jfEditora.setBounds(30, 160, 250, 30);
		add(jfEditora);
		
		jfAnoDePublicacao = new JTextField();
		jfAnoDePublicacao.setBounds(30, 220, 250, 30);
		add(jfAnoDePublicacao);
		
		jfUnidades = new JTextField();
		jfUnidades.setBounds(30, 275,250, 30);
		add(jfUnidades);
		
		jfPreco = new JTextField();
		jfPreco.setBounds(30, 330, 250, 30);
		add(jfPreco);
		
		jaResumo = new JTextArea();
		jaResumo.setLineWrap(true);
		jaResumo.setWrapStyleWord(true);
		jaResumo.setBounds(350, 50, 300, 230);
		add(jaResumo);
		
		JLabel lGenero = new JLabel("Gênero:");
		lGenero.setBounds(350, 300, 180, 40);
		lGenero.setFont(new Font("Arial", Font.BOLD,14));
		add(lGenero);
	}
	
	private void adicionarJButton() {
		OuvinteDoBotaoCadastrarLivro ouvinte = new OuvinteDoBotaoCadastrarLivro(this);
		botao = new JButton("Cadastrar");
		botao.addActionListener(ouvinte);
		botao.setBounds(480, 470, 150, 30);
		add(botao);
	}
	
	private void adicionarTextFields(Livro livro) {
		jfTitulo = new JTextField(livro.getTitulo());
		jfTitulo.setBounds(30, 45, 250, 30);
		add(jfTitulo);
		
		jfIdioma = new JTextField(livro.getIdioma());
		jfIdioma.setBounds(30, 105, 250, 30);
		add(jfIdioma);
		
		jfEditora = new JTextField(livro.getEditora());
		jfEditora.setBounds(30, 160, 250, 30);
		add(jfEditora);
		
		jfAnoDePublicacao = new JTextField(String.valueOf(livro.getAnoDePublicacao()));
		jfAnoDePublicacao.setBounds(30, 220, 250, 30);
		add(jfAnoDePublicacao);
		
		jfUnidades = new JTextField(String.valueOf(livro.getUnidades()));
		jfUnidades.setBounds(30, 275,250, 30);
		add(jfUnidades);
		
		jfPreco = new JTextField(String.valueOf(livro.getPreco()));
		jfPreco.setBounds(30, 330, 250, 30);
		add(jfPreco);
		
		jaResumo = new JTextArea(livro.getResumo());
		jaResumo.setLineWrap(true);
		jaResumo.setWrapStyleWord(true);
		jaResumo.setBounds(350, 50, 300, 230);
		add(jaResumo);
	}
	
	private void adicionarJButton(Livro livro) {
		OuvinteDoBotaoSalvarEdicaoLivro ouvinte = new OuvinteDoBotaoSalvarEdicaoLivro(this, livro);
		botao = new JButton("Salvar");
		botao.addActionListener(ouvinte);
		botao.setBounds(480, 470, 150, 30);
		add(botao);
	}
	
	public JTextField getJfTitulo() {
		return jfTitulo;
	}

	public JTextArea getJaResumo() {
		return jaResumo;
	}

	public JTextField getJfIdioma() {
		return jfIdioma;
	}

	public JTextField getJfEditora() {
		return jfEditora;
	}

	public JTextField getJfAnoDePublicacao() {
		return jfAnoDePublicacao;
	}

	public JTextField getJfUnidades() {
		return jfUnidades;
	}

	public JTextField getJfPreco() {
		return jfPreco;
	}	
}


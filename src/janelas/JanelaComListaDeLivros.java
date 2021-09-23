package janelas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modelo.CentralDeInformacoes;
import modelo.Cliente;
import modelo.Livreiro;
import modelo.Livro;
import modelo.Usuario;
import modelo.atributos.estaticos.AtributosProjeto;
import ouvintes.OuvinteDoBotaoDetalharLivro;
import ouvintes.OuvinteDoMenuDeRelatorios;

public class JanelaComListaDeLivros extends JanelaComMenuLivros {
	
	private JPanel painelNorte;
	private JPanel painelCentro;
	private JPanel painelSul;
	private DefaultTableModel modeloTabela;
	private JTable tabela;
	private JButton btDetalharLivro;
	private JButton btExtra;
	private JTextField tfPesquisar;
	private JButton btPesquisar;
	private String[] tipos = {"Literatura", "Desenvolvimento Pessoal", "Periódico", "Técnico"};
	private JComboBox<String> cbTipos;
	private JComboBox<String> cbOrdem;
	
	public JanelaComListaDeLivros(Usuario usuario) {
		super();
		setTitle("Livros disponíveis");
		adicionarPainelNorte();
		adicionarPainelCentro();
		adicionarPainelSul();
		configurarBotao(usuario);
		setVisible(true);
	}

	private class OuvinteDoBotaoAcessarColecao implements ActionListener {
		private JFrame janela;
		
		public OuvinteDoBotaoAcessarColecao(JFrame janela) {
			this.janela = janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			janela.dispose();
			
			Cliente usuario = (Cliente) AtributosProjeto.USUARIO_LOGADO;
			new JanelaComColecaoDoCliente(usuario); 
		}
	}
	
	private class OuvinteDoBotaoCadastrarNovoLivro implements ActionListener {
		private JFrame janela;
		
		public OuvinteDoBotaoCadastrarNovoLivro(JFrame janela) {
			this.janela = janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			String resposta = (String) JOptionPane.showInputDialog(janela, "Selecione o tipo do livro que deseja cadastrar", "Cadastrar novo livro", JOptionPane.PLAIN_MESSAGE, null, tipos, tipos[0]);
		
			if (resposta != null) {
				janela.dispose();
				
				switch (resposta) {
				case "Literatura":
					new CadastroLivroLiteratura();
					break;
				
				case "Desenvolvimento Pessoal":
					new CadastroLivroDesenvolvimentoPessoal();
					break;
				
				case "Periódico":
					new CadastroLivroPeriodico();
					break;
				
				case "Técnico":
					new CadastroLivroTecnico();
					break;
				}
			}
		}
	}
	
	private void configurarBotao(Usuario usuario) {
			
		if (usuario instanceof Livreiro) {
			OuvinteDoBotaoCadastrarNovoLivro ouvinte = new OuvinteDoBotaoCadastrarNovoLivro(this);
			btExtra.setText("Cadastrar livro");
			btExtra.addActionListener(ouvinte);
			
			OuvinteDoMenuDeRelatorios ouvinteMenu = new OuvinteDoMenuDeRelatorios(this);
			JMenu opcoesRelatorio = new JMenu("Relatórios do livreiro");
			opcoesRelatorio.setMnemonic('r');
			
			JMenuItem itemLivrosCadastrados = new JMenuItem("Gerar relatório dos livros cadastrados");
			itemLivrosCadastrados.addActionListener(ouvinteMenu);
			opcoesRelatorio.add(itemLivrosCadastrados);
			
			this.getJMenuBar().add(opcoesRelatorio);
			
		} else {
			OuvinteDoBotaoAcessarColecao ouvinte = new OuvinteDoBotaoAcessarColecao(this);
			btExtra.setText("Acessar coleção");
			btExtra.addActionListener(ouvinte);
		}		
	}
	
	public class OuvinteDoComboBoxDeOrdem implements ActionListener {

		private JanelaComListaDeLivros janela;
		
		public OuvinteDoComboBoxDeOrdem(JanelaComListaDeLivros janela) {
			this.janela = janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			// Organizar os livros de acordo com a ordem selecionada
		}
	}
	
	public class OuvinteDoComboBoxDeFiltro implements ActionListener {

		private JanelaComListaDeLivros janela;
		
		public OuvinteDoComboBoxDeFiltro(JanelaComListaDeLivros janela) {
			this.janela = janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			String tipo = (String) cbTipos.getSelectedItem();
			
			
		}
	}
	
	private void adicionarPainelNorte() {
		EmptyBorder borda = new EmptyBorder(30, 50, 0, 50);
		painelNorte = new JPanel();
		painelNorte.setBorder(borda);
		painelNorte.setLayout(new GridLayout(0, 6, 10, 10));
		
		tfPesquisar = new JTextField();
		painelNorte.add(tfPesquisar);
		btPesquisar = new JButton("Pesquisar");
		painelNorte.add(btPesquisar);
		
		JLabel lbFiltrar = new JLabel("Filtrar por tipo:");
		lbFiltrar.setHorizontalAlignment(JLabel.RIGHT);
		painelNorte.add(lbFiltrar);
		
		cbTipos = new JComboBox<String>(tipos);
		painelNorte.add(cbTipos);
		
		JLabel lbOrdenar = new JLabel("Ordenar por:");
		lbOrdenar.setHorizontalAlignment(JLabel.RIGHT);
		painelNorte.add(lbOrdenar);
		
		String[] ordem = {"Sem ordem específica", "Ordem Alfabética", "Ordem de avaliação"};
		cbOrdem = new JComboBox<String>(ordem);
		painelNorte.add(cbOrdem);
		
		add(painelNorte, BorderLayout.NORTH);
	}

	private void adicionarPainelCentro() {
		EmptyBorder borda = new EmptyBorder(30, 50, 30, 50);
		painelCentro = new JPanel();
		painelCentro.setBorder(borda);
		painelCentro.setLayout(new GridLayout());
		
		modeloTabela = new DefaultTableModel();
		modeloTabela.addColumn("Título");
		modeloTabela.addColumn("ID");
		modeloTabela.addColumn("Tipo");
		modeloTabela.addColumn("Gênero");
		modeloTabela.addColumn("Editora");
		modeloTabela.addColumn("Ano de publicação");
		
		CentralDeInformacoes central = CentralDeInformacoes.getInstance();
		ArrayList<Livro> livros = central.getTodosOsLivros();
		
		tabela = new JTable(modeloTabela);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tabela.getColumnModel().getColumn(0).setMinWidth(200);
		
		if (livros.size() > 0) {
			for (Livro livro: livros) {
				Object[] linha = new Object[6];
				
				linha[0] = livro.getTitulo();
				linha[1] = livro.getId();
				linha[2] = livro.getTipo();
				linha[3] = livro.getGenero();
				linha[4] = livro.getEditora();
				linha[5] = livro.getAnoDePublicacao();
				
				modeloTabela.addRow(linha);
			}
		}
		
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		
		// Centralizar conteúdo das células
		for (int i = 0; i < 6; i++) {
			tabela.getColumnModel().getColumn(i).setCellRenderer(cr);
		}
		
		JScrollPane painelTabela = new JScrollPane(tabela);
		painelCentro.add(painelTabela);
		add(painelCentro, BorderLayout.CENTER);
	}
	
	private void adicionarPainelSul() {
		EmptyBorder borda = new EmptyBorder(0, 200, 30, 200);
		painelSul = new JPanel();
		painelSul.setBorder(borda);
		painelSul.setLayout(new GridLayout(0, 2, 100, 100));
		
		btDetalharLivro = new JButton("Detalhar livro");
		OuvinteDoBotaoDetalharLivro ouvinte = new OuvinteDoBotaoDetalharLivro(this, tabela);
		btDetalharLivro.addActionListener(ouvinte);
		painelSul.add(btDetalharLivro);
		
		btExtra = new JButton("Botão");
		painelSul.add(btExtra);
		
		add(painelSul, BorderLayout.SOUTH);
	}

	public JPanel getPainelNorte() {
		return painelNorte;
	}

	public JPanel getPainelCentro() {
		return painelCentro;
	}

	public JPanel getPainelSul() {
		return painelSul;
	}

	public JTable getTabela() {
		return tabela;
	}

	public JButton getBtDetalharLivro() {
		return btDetalharLivro;
	}

	public JButton getBtExtra() {
		return btExtra;
	}

	public JTextField getTfPesquisar() {
		return tfPesquisar;
	}

	public JButton getBtPesquisar() {
		return btPesquisar;
	}

	public JComboBox<String> getCbTipos() {
		return cbTipos;
	}

	public JComboBox<String> getCbOrdem() {
		return cbOrdem;
	}
}

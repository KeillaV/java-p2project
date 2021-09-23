package janelas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modelo.*;
import modelo.atributos.estaticos.AtributosProjeto;
import ouvintes.OuvinteDoBotaoDetalharLivro;

public class JanelaComColecaoDoCliente extends JanelaComMenuLivros {
	
	private JTable tabela;
	private JButton btDetalharLivro;
	private JButton btAvaliarLivro;
	
	public JanelaComColecaoDoCliente(Cliente usuario) {
		super();
		setTitle("Coleção de livros");
		adicionarPainelNorte(usuario);
		adicionarPainelCentro(usuario);
		adicionarPainelSul();
		setVisible(true);
	}

	private void adicionarPainelNorte(Cliente usuario) {
		EmptyBorder borda = new EmptyBorder(30, 50, 0, 50);
		
		JPanel painelNorte = new JPanel();
		painelNorte.setBorder(borda);
		
		JLabel lbTitulo = new JLabel("Coleção de " + usuario.getNome());
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 22));
		lbTitulo.setHorizontalAlignment(JLabel.CENTER);
		painelNorte.add(lbTitulo);
		
		add(painelNorte, BorderLayout.NORTH);
	}

	public class OuvinteDoBotaoAvaliarLivro implements ActionListener {
		
		private JFrame janela;
		
		public OuvinteDoBotaoAvaliarLivro(JFrame janela) {
			this.janela = janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			int indiceDoLivroSelecionado = tabela.getSelectedRow();
			
			if (indiceDoLivroSelecionado == -1) {
				JOptionPane.showMessageDialog(janela, "Selecione o livro primeiro!", "Aviso", JOptionPane.WARNING_MESSAGE, null);
			
			} else {
				Avaliacao[] notas = Avaliacao.values();
				
				Avaliacao avaliacao = (Avaliacao) JOptionPane.showInputDialog(janela, "Selecione uma nota para o livro", "Avaliação", JOptionPane.PLAIN_MESSAGE, null, notas, notas[0]);
	
				if (avaliacao != null) {
					try {
						CentralDeInformacoes central = CentralDeInformacoes.getInstance();
						
						long idLivro = (long) tabela.getValueAt(indiceDoLivroSelecionado, 1);
						Livro livroSelecionado = central.recuperarLivro(idLivro);
							
						Cliente usuario = (Cliente) central.recuperarUsuario(AtributosProjeto.USUARIO_LOGADO.getEmail());
								 
						Colecao colecao = usuario.getColecaoDeLivros();
						colecao.getLivrosDaColecao().put(livroSelecionado, avaliacao.getNota());
						
						Persistencia persistencia = new Persistencia();
						persistencia.salvarCentral(central, "central.xml");
						
						DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
						modelo.setValueAt(avaliacao.getNota(), indiceDoLivroSelecionado, 2);
						tabela.repaint();
								
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(janela, "Não foi possível avaliar o livro!", "Erro", JOptionPane.ERROR_MESSAGE, null);
					}
				}
			}	
		}
	}
	
	private void adicionarPainelCentro(Cliente usuario) {
		EmptyBorder borda = new EmptyBorder(30, 50, 30, 50);
		JPanel painelCentro = new JPanel();
		painelCentro.setBorder(borda);
		painelCentro.setLayout(new GridLayout());
		
		DefaultTableModel modeloTabela = new DefaultTableModel();
		modeloTabela.addColumn("Livro");
		modeloTabela.addColumn("ID");
		modeloTabela.addColumn("Avaliação");
		
		tabela = new JTable(modeloTabela);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		tabela.getColumnModel().getColumn(0).setMinWidth(400);
		tabela.getColumnModel().getColumn(2).setMinWidth(300);
		
		Colecao colecaoCliente = usuario.getColecaoDeLivros();
		
		if (colecaoCliente != null) {
			HashMap<Livro, String> livros = colecaoCliente.getLivrosDaColecao();
			
			if (livros.size() > 0) {
				for (Map.Entry<Livro, String> valores: livros.entrySet()) {
					Object[] linha = new Object[3];
					Livro livro = valores.getKey();
					
					linha[0] = livro.getTitulo();
					linha[1] = livro.getId();
					linha[2] = valores.getValue();
					
					modeloTabela.addRow(linha);
				}
			}
		}
		
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		
		// Centralizar conteúdo das células
		for (int i = 0; i < 3; i++) {
			tabela.getColumnModel().getColumn(i).setCellRenderer(cr);
		}
		
		JScrollPane painelTabela = new JScrollPane(tabela);
		painelCentro.add(painelTabela);
		add(painelCentro, BorderLayout.CENTER);	
	}

	private void adicionarPainelSul() {
		EmptyBorder bordaSul = new EmptyBorder(0, 200, 30, 200);
		JPanel painelSul = new JPanel();
		painelSul.setBorder(bordaSul);
		painelSul.setLayout(new GridLayout(0, 2, 100, 100));
		
		btDetalharLivro = new JButton("Detalhar livro");
		OuvinteDoBotaoDetalharLivro ouvinteDetalhar = new OuvinteDoBotaoDetalharLivro(this, tabela );
		btDetalharLivro.addActionListener(ouvinteDetalhar);
		painelSul.add(btDetalharLivro);
		
		btAvaliarLivro = new JButton("Avaliar livro");
		OuvinteDoBotaoAvaliarLivro ouvinteAvaliar = new OuvinteDoBotaoAvaliarLivro(this);
		btAvaliarLivro.addActionListener(ouvinteAvaliar);
		painelSul.add(btAvaliarLivro);
		
		add(painelSul, BorderLayout.SOUTH);
	}

	public JTable getTabela() {
		return tabela;
	}

	public JButton getBtDetalharLivro() {
		return btDetalharLivro;
	}
}

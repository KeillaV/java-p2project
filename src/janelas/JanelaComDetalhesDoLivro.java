package janelas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modelo.CentralDeInformacoes;
import modelo.Cliente;
import modelo.Comentario;
import modelo.Livreiro;
import modelo.Livro;
import modelo.LivroDesenvolvimentoPessoal;
import modelo.LivroLiteratura;
import modelo.LivroPeriodico;
import modelo.LivroTecnico;
import modelo.Mensageiro;
import modelo.Persistencia;
import modelo.Usuario;
import modelo.atributos.estaticos.AtributosProjeto;
import ouvintes.OuvinteDoBotaoComprarLivro;
import ouvintes.OuvinteDoBotaoSalvarEdicaoLivro;

public class JanelaComDetalhesDoLivro extends JanelaComMenuLivros {

	private Font fonteTitulos = new Font("Arial", Font.BOLD, 16);
	private Font fonteTexto = new Font("Arial", Font.PLAIN, 16);
	private EmptyBorder borda = new EmptyBorder(20, 30, 10, 30);
	private JPanel painelLabels;
	private JButton btPrincipal;
	private JButton btAdicionarAColecao;
	private JButton btAdicionarComentario;
	private JButton btRemoverComentario;
	private JTable tabelaComentarios;
	private JTextArea tfComentario;
	
	public JanelaComDetalhesDoLivro(Livro livro, Usuario usuario) {
		super();
		setTitle("Informações do livro");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		adicionarDadosDoLivro(livro); 
		adicionarDadosExtras(livro);
		adicionarBotoesPrincipais(livro);
		adicionarComentarios(livro);
		
		int visualizacoes = livro.getNumeroDeVisualizacoes() + 1;
		livro.setNumeroDeVisualizacoes(visualizacoes);
		
		CentralDeInformacoes central = CentralDeInformacoes.getInstance();
		Persistencia persistencia = new Persistencia();
		persistencia.salvarCentral(central, "central.xml");
		
		setVisible(true);
	}

	private class OuvinteDoBotaoAdicionarComentario implements ActionListener {
		private JFrame janela;
		private Livro livro;
		
		public OuvinteDoBotaoAdicionarComentario(JFrame janela, Livro livro) {
			this.janela = janela;
			this.livro = livro;
		}
		
		public void actionPerformed(ActionEvent e) {
			String mensagem = tfComentario.getText();
			
			if (!mensagem.isEmpty()) {
				Comentario comentario = new Comentario(AtributosProjeto.USUARIO_LOGADO, mensagem);
				livro.adicionarComentario(comentario);
			
				CentralDeInformacoes central = CentralDeInformacoes.getInstance();
				Persistencia persistencia = new Persistencia();
				persistencia.salvarCentral(central, "central.xml");
				
				Object[] linha = {comentario.getAutor(), comentario.getId(), comentario.getMensagem()};
				DefaultTableModel modelo = (DefaultTableModel) tabelaComentarios.getModel();
				modelo.addRow(linha);
				tabelaComentarios.repaint();
				
				JOptionPane.showMessageDialog(janela, "Comentário adicionado!", "Comentário", JOptionPane.PLAIN_MESSAGE, null);
				
			} else {
				JOptionPane.showMessageDialog(janela, "Escreva um comentário primeiro!", "Aviso", JOptionPane.WARNING_MESSAGE, null);
			}
		}
	}
	
	private class OuvinteDoBotaoRemoverComentario implements ActionListener {
		private JFrame janela;
		private Livro livro;
		
		public OuvinteDoBotaoRemoverComentario(JFrame janela, Livro livro) {
			this.janela = janela;
			this.livro = livro;
		}

		public void actionPerformed(ActionEvent e) {
			int indiceDoComentarioSelecionado = tabelaComentarios.getSelectedRow();
			
			if (indiceDoComentarioSelecionado == -1) {
				JOptionPane.showMessageDialog(janela, "Selecione o comentário primeiro!", "Aviso", JOptionPane.WARNING_MESSAGE, null);
			
			} else {
				DefaultTableModel modelo = (DefaultTableModel) tabelaComentarios.getModel();
				long id = (long) modelo.getValueAt(indiceDoComentarioSelecionado, 1);
				Comentario comentario = livro.recuperarComentario(id);
				
				// Clientes só podem remover os seus próprios comentários, o livreiro pode remover todos
				if (AtributosProjeto.USUARIO_LOGADO instanceof Cliente && !comentario.getAutor().equals(AtributosProjeto.USUARIO_LOGADO)) {
					JOptionPane.showMessageDialog(janela, "Você só pode remover os seus comentários!", "Erro", JOptionPane.ERROR_MESSAGE, null);
					
				} else {
					livro.removerComentario(comentario);
					
					CentralDeInformacoes central = CentralDeInformacoes.getInstance();
					Persistencia persistencia = new Persistencia();
					persistencia.salvarCentral(central, "central.xml");
					
					modelo.removeRow(indiceDoComentarioSelecionado);
					tabelaComentarios.repaint();
					
					JOptionPane.showMessageDialog(janela, "Comentário removido com sucesso!", "Comentário removido", JOptionPane.PLAIN_MESSAGE, null);
				}
			}
		}
	}
	
	private void adicionarComentarios(Livro livro) {
		JPanel painelComentarios = new JPanel();
		painelComentarios.setBorder(borda);
		painelComentarios.setLayout(new BoxLayout(painelComentarios, BoxLayout.Y_AXIS));
		painelComentarios.setMinimumSize(new Dimension(900, 350));
		painelComentarios.setMaximumSize(new Dimension(1000, 350));
		
		DefaultTableModel modeloTabela = new DefaultTableModel();
		modeloTabela.addColumn("Usuário");
		modeloTabela.addColumn("ID");
		modeloTabela.addColumn("Comentário");
		
		tabelaComentarios = new JTable(modeloTabela);
		tabelaComentarios.setMinimumSize(new Dimension(900, 200));
		tabelaComentarios.setMaximumSize(new Dimension(1000, 200));
		tabelaComentarios.getColumnModel().getColumn(0).setMinWidth(200);
		tabelaComentarios.getColumnModel().getColumn(0).setMinWidth(150);
		tabelaComentarios.getColumnModel().getColumn(2).setMinWidth(550);
		
		ArrayList<Comentario> comentarios = livro.getListaDeComentarios();
		
		if (comentarios.size() > 0) {
			for (Comentario comentario: comentarios) {
				Object[] linha = new Object[3];
				
				linha[0] = comentario.getAutor();
				linha[1] = comentario.getId();
				linha[2] = comentario.getMensagem();
				
				modeloTabela.addRow(linha);
				
			}	
		}
	
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		
		// Centralizar conteúdo das células
		for (int i = 0; i < 3; i++) {
			tabelaComentarios.getColumnModel().getColumn(i).setCellRenderer(cr);
		}
		
		JScrollPane painelTabela = new JScrollPane(tabelaComentarios);
		painelComentarios.add(painelTabela);
		
		painelComentarios.add(Box.createRigidArea(new Dimension(0, 20)));
		tfComentario = new JTextArea();
		tfComentario.setFont(fonteTexto);
		tfComentario.setLineWrap(true);
		tfComentario.setWrapStyleWord(true);
		
		JScrollPane spComentario = new JScrollPane(tfComentario);
		spComentario.setMinimumSize(new Dimension(800, 50));
		spComentario.setMaximumSize(new Dimension(1000, 50));
		painelComentarios.add(spComentario);
		
		JPanel painelBotoesComentario = new JPanel();
		painelBotoesComentario.setBorder(borda);
		painelBotoesComentario.setLayout(new FlowLayout());
		painelBotoesComentario.setMaximumSize(new Dimension(800, 30));
		
		OuvinteDoBotaoAdicionarComentario ouvinteAdicionar = new OuvinteDoBotaoAdicionarComentario(this, livro);
		btAdicionarComentario = new JButton("Adicionar comentário");
		btAdicionarComentario.addActionListener(ouvinteAdicionar);
		btAdicionarComentario.setMaximumSize(new Dimension(100, 40));
		btAdicionarComentario.setAlignmentX(Component.RIGHT_ALIGNMENT);
		painelBotoesComentario.add(btAdicionarComentario);
		
		painelBotoesComentario.add(Box.createRigidArea(new Dimension(10, 0)));
		
		OuvinteDoBotaoRemoverComentario ouvinteRemover = new OuvinteDoBotaoRemoverComentario(this, livro);
		btRemoverComentario = new JButton("Remover comentário");
		btRemoverComentario.addActionListener(ouvinteRemover);
		btRemoverComentario.setMaximumSize(new Dimension(100, 40));
		btRemoverComentario.setAlignmentX(Component.RIGHT_ALIGNMENT);
		painelBotoesComentario.add(btRemoverComentario);
		painelComentarios.add(painelBotoesComentario);

		add(painelComentarios);
	}
	
	private class OuvinteDoBotaoIndicarInteresse implements ActionListener {
		private Livro livro;
		private JFrame janela;
		
		public OuvinteDoBotaoIndicarInteresse(JFrame janela, Livro livro) {
			this.janela = janela;
			this.livro = livro;
		}
		
		public void actionPerformed(ActionEvent e) {
			String email = AtributosProjeto.USUARIO_LOGADO.getEmail();
			
			if (livro.getEmailsDosClientesInteressados().contains(email)) {
				JOptionPane.showMessageDialog(janela, "Você já indicou interesse nesse livro!", "Aviso", JOptionPane.WARNING_MESSAGE, null);
				
			} else {
				livro.getEmailsDosClientesInteressados().add(email);
				JOptionPane.showMessageDialog(janela, "Quando o livro estiver novamente disponível, você receberá um aviso por email!", "Interesse no livro", JOptionPane.PLAIN_MESSAGE, null);
			
				CentralDeInformacoes central = CentralDeInformacoes.getInstance();
				Persistencia persistencia = new Persistencia();
				persistencia.salvarCentral(central, "central.xml");
			}	
		}
	}
	
	private class OuvinteDoBotaoAdicionarAColecao implements ActionListener {
		private Livro livro;
		private JFrame janela;
		
		public OuvinteDoBotaoAdicionarAColecao(JFrame janela, Livro livro) {
			this.janela = janela;
			this.livro = livro;
		}
		
		public void actionPerformed(ActionEvent e) {
			CentralDeInformacoes central = CentralDeInformacoes.getInstance();
			Cliente usuario;
			
			try {
				usuario = (Cliente) central.recuperarUsuario(AtributosProjeto.USUARIO_LOGADO.getEmail());
				HashMap<Livro, String> livrosDaColecao = usuario.getColecaoDeLivros().getLivrosDaColecao();
				
				// Testando se o livro já foi adicionado na coleção do cliente:
				 if (!livrosDaColecao.containsKey(livro)) {
					usuario.getColecaoDeLivros().adicionarLivro(livro);
					 
					JOptionPane.showMessageDialog(janela, livro.getTitulo() + " foi adicionado à sua coleção!", "Adicionar livro à coleção", JOptionPane.PLAIN_MESSAGE, null);
						
					// Salvando os dados da coleção do usuário
					Persistencia persistencia = new Persistencia();
					persistencia.salvarCentral(central, "central.xml");
					
				 } else {
					 JOptionPane.showMessageDialog(janela, livro.getTitulo() + " já está na sua coleção!", "Aviso", JOptionPane.PLAIN_MESSAGE, null);
				 }
		
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(janela, "Não foi possível adicionar o livro à sua coleção!", "Erro", JOptionPane.ERROR_MESSAGE, null);
			}
		}	
	}
	
	private class OuvinteDoBotaoEditarLivro implements ActionListener {
		private Livro livro;
		private JFrame janela;

		public OuvinteDoBotaoEditarLivro(JFrame janela, Livro livro) {
			this.janela = janela;
			this.livro = livro;
		}
		
		public void actionPerformed(ActionEvent e) {
			janela.dispose();
			
			if (livro instanceof LivroLiteratura) {
				new CadastroLivroLiteratura(livro);
				
			} else if (livro instanceof LivroDesenvolvimentoPessoal) {
				new CadastroLivroDesenvolvimentoPessoal(livro);
				
			} else if (livro instanceof LivroPeriodico) {
				new CadastroLivroPeriodico(livro);
			} else {
				new CadastroLivroTecnico(livro);
			}
		}
	}
	
	private void adicionarBotoesPrincipais(Livro livro) {
		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout());
		painelBotoes.setMaximumSize(new Dimension(1000, 70));
		painelBotoes.setBorder(borda);
		
		btPrincipal = new JButton();
		btPrincipal.setMaximumSize(new Dimension(100, 30));
		btPrincipal.setHorizontalAlignment(JLabel.CENTER);
		btPrincipal.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Usuario usuario = AtributosProjeto.USUARIO_LOGADO;
		
		if (usuario instanceof Livreiro) {
			OuvinteDoBotaoEditarLivro ouvinteEditar = new OuvinteDoBotaoEditarLivro(this, livro);
			btPrincipal.setText("Editar livro");
			btPrincipal.addActionListener(ouvinteEditar);
			
		} else {
			if (livro.getUnidades() == 0) {
				OuvinteDoBotaoIndicarInteresse ouvinteIndicar = new OuvinteDoBotaoIndicarInteresse(this, livro);
				btPrincipal.setText("Indicar interesse");
				btPrincipal.addActionListener(ouvinteIndicar);
			} else {
				OuvinteDoBotaoComprarLivro ouvinteComprar = new OuvinteDoBotaoComprarLivro(this, livro);
				btPrincipal.setText("Comprar livro");
				btPrincipal.addActionListener(ouvinteComprar);
			}
			
			
			OuvinteDoBotaoAdicionarAColecao ouvinteAdicionar = new OuvinteDoBotaoAdicionarAColecao(this, livro);
			btAdicionarAColecao = new JButton("Adicionar à coleção");
			btAdicionarAColecao.setHorizontalAlignment(JLabel.CENTER);
			btAdicionarAColecao.setAlignmentX(Component.CENTER_ALIGNMENT);
			btAdicionarAColecao.addActionListener(ouvinteAdicionar);
			
			painelBotoes.add(btAdicionarAColecao);
			painelBotoes.add(Box.createRigidArea(new Dimension(10, 0)));
		}
		
		painelBotoes.add(btPrincipal);
		add(painelBotoes);
	}

	private void adicionarDadosDoLivro(Livro livro) {
		Font fonteCabecalho = new Font("Arial", Font.BOLD, 22);
		
		add(Box.createRigidArea(new Dimension(0, 50)));
		JLabel lbTitulo = new JLabel(livro.getTitulo());
		lbTitulo.setFont(fonteCabecalho);
		lbTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lbTitulo);
		
		add(Box.createRigidArea(new Dimension(0, 10)));
		JLabel lbPreco = new JLabel("R$ " + livro.getPreco());
		lbPreco.setFont(fonteCabecalho);
		lbPreco.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lbPreco);
		
		JPanel painelInfo = new JPanel();
		painelInfo.setBorder(borda);
		painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));
		painelInfo.setMaximumSize(new Dimension(1000, 200));
		
		JTextArea tfResumo = new JTextArea(livro.getResumo());
		tfResumo.setFont(fonteTexto);
		tfResumo.setEnabled(false);
		tfResumo.setDisabledTextColor(Color.DARK_GRAY);
		tfResumo.setBackground(null);
		tfResumo.setLineWrap(true);
		tfResumo.setWrapStyleWord(true);
		JScrollPane painelResumo = new JScrollPane(tfResumo);
		painelResumo.setMaximumSize(new Dimension(1000, 80));
		painelResumo.setMinimumSize(new Dimension(900, 80));
		painelInfo.add(painelResumo);
		
		painelLabels = new JPanel();
		painelLabels.setLayout(new GridLayout(3, 2, 0, 5));
		painelLabels.setMaximumSize(new Dimension(1000, 120));
		painelLabels.setBorder(borda);
		
		JLabel lbIdioma = new JLabel("Idioma: " + livro.getIdioma());
		lbIdioma.setFont(fonteTitulos);
		lbIdioma.setHorizontalAlignment(JLabel.CENTER);
		painelLabels.add(lbIdioma);
		
		JLabel lbEditora = new JLabel("Editora: " + livro.getEditora());
		lbEditora.setFont(fonteTitulos);
		lbEditora.setHorizontalAlignment(JLabel.CENTER);
		painelLabels.add(lbEditora);
		
		JLabel lbAnoDePublicacao = new JLabel("Ano de publicação: " + livro.getAnoDePublicacao());
		lbAnoDePublicacao.setFont(fonteTitulos);
		lbAnoDePublicacao.setHorizontalAlignment(JLabel.CENTER);
		painelLabels.add(lbAnoDePublicacao);
		
		JLabel lbUnidades = new JLabel("Unidades disponíveis: " + livro.getUnidades());
		lbUnidades.setFont(fonteTitulos);
		lbUnidades.setHorizontalAlignment(JLabel.CENTER);
		painelLabels.add(lbUnidades);
		
		JLabel lbTipo = new JLabel("Tipo: " + livro.getTipo());
		lbTipo.setFont(fonteTitulos);
		lbTipo.setHorizontalAlignment(JLabel.CENTER);
		painelLabels.add(lbTipo);
		
		JLabel lbGenero = new JLabel("Gênero: " + livro.getGenero());
		lbGenero.setFont(fonteTitulos);
		lbGenero.setHorizontalAlignment(JLabel.CENTER);
		painelLabels.add(lbGenero);
		painelInfo.add(painelLabels);
		
		add(painelInfo);	
	}

	private void adicionarDadosExtras(Livro livro) {
		if (livro instanceof LivroLiteratura || livro instanceof LivroDesenvolvimentoPessoal) {
			
			JLabel lbAutores = new JLabel();
			StringBuilder stringAutores = new StringBuilder();
			stringAutores.append("Autores: ");
			
			ArrayList<String> autores = null;
			if (livro instanceof LivroLiteratura) {
				LivroLiteratura livroL = (LivroLiteratura) livro;
				autores = livroL.getAutores();
			} else {
				LivroDesenvolvimentoPessoal livroDP = (LivroDesenvolvimentoPessoal) livro;
				autores = livroDP.getAutores();
			}
			 
			for (int i = 0; i < autores.size(); i++) {
				stringAutores.append(autores.get(i));
				
				if (i == (autores.size() - 1)) {
					stringAutores.append(".");
				} else {
					stringAutores.append(", ");
				}
			}
			
			lbAutores.setText(stringAutores.toString());
			lbAutores.setFont(fonteTitulos);
			lbAutores.setHorizontalAlignment(JLabel.CENTER);
			painelLabels.add(lbAutores);
			
		} else if (livro instanceof LivroPeriodico) {
			LivroPeriodico livroP = (LivroPeriodico) livro;
			
			JLabel lbNumeroDaEdicao = new JLabel("Número da edição: " + livroP.getNumeroDaEdicao());
			lbNumeroDaEdicao.setFont(fonteTitulos);
			lbNumeroDaEdicao.setHorizontalAlignment(JLabel.CENTER);
			painelLabels.add(lbNumeroDaEdicao);
			
			JLabel lbMesDeLancamento = new JLabel("Mês de lançamento: " + livroP.getMesDeLancamento());
			lbMesDeLancamento.setFont(fonteTitulos);
			lbMesDeLancamento.setHorizontalAlignment(JLabel.CENTER);
			painelLabels.add(lbMesDeLancamento);
			
		} else if (livro instanceof LivroTecnico) {
			LivroTecnico livroT = (LivroTecnico) livro;
			
			JLabel lbAssunto = new JLabel("Assunto: " + livroT.getAssunto());
			lbAssunto.setFont(fonteTitulos);
			lbAssunto.setHorizontalAlignment(JLabel.CENTER);
			painelLabels.add(lbAssunto);
		}
	}
}

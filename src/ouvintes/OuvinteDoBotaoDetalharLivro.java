package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import janelas.JanelaComDetalhesDoLivro;
import modelo.CentralDeInformacoes;
import modelo.Livro;
import modelo.atributos.estaticos.AtributosProjeto;

public class OuvinteDoBotaoDetalharLivro implements ActionListener {
	private JFrame janela;
	private JTable tabela;
	
	public OuvinteDoBotaoDetalharLivro(JFrame janela, JTable tabela) {
		this.janela = janela;
		this.tabela = tabela;
	}
	
	// Esse ouvinte é usado na janela com lista de livros e na janela com cadastro de clientes, pois ambas possuem o botão de detalhar um livro da tabela
	public void actionPerformed(ActionEvent e) {
		int indiceDoLivroSelecionado = tabela.getSelectedRow();
		
		if (indiceDoLivroSelecionado == -1) {
			JOptionPane.showMessageDialog(null, "Selecione o livro primeiro!", "Aviso", JOptionPane.WARNING_MESSAGE, null);
		} else {
			CentralDeInformacoes central = CentralDeInformacoes.getInstance();
			
			// Recuperar id do livro na tabela
			long idLivro = (long) tabela.getValueAt(indiceDoLivroSelecionado, 1);
			Livro livroSelecionado = central.recuperarLivro(idLivro);

			janela.dispose();
			new JanelaComDetalhesDoLivro(livroSelecionado, AtributosProjeto.USUARIO_LOGADO);
		}
	}
}
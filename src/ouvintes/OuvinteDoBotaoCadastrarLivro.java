package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JOptionPane;

import janelas.*;
import modelo.*;
import modelo.atributos.estaticos.AtributosProjeto;

public class OuvinteDoBotaoCadastrarLivro implements ActionListener {
	private JanelaCadastroLivro janela;
	
	public OuvinteDoBotaoCadastrarLivro(JanelaCadastroLivro janela) {
		this.janela = janela;
	}

	public void actionPerformed(ActionEvent e) {
		String titulo = janela.getJfTitulo().getText();
		String resumo = janela.getJaResumo().getText();
		String idioma = janela.getJfIdioma().getText();
		String editora = janela.getJfEditora().getText();
		String stringAno  = janela.getJfAnoDePublicacao().getText();
		String stringUnidades = janela.getJfUnidades().getText();
		String stringPreco = janela.getJfPreco().getText();
		
		if (titulo.isEmpty() || resumo.isEmpty() || idioma.isEmpty() || editora.isEmpty() || stringAno.isEmpty() || stringUnidades.isEmpty() || stringPreco.isEmpty()) {
			JOptionPane.showMessageDialog(janela, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE, null);
			
		} else {
			CentralDeInformacoes central = CentralDeInformacoes.getInstance();
			Persistencia persistencia = new Persistencia();
			
			try {
				int ano  = Integer.parseInt(stringAno);
				int unidades = Integer.parseInt(stringUnidades);
				float preco = Float.parseFloat(stringPreco);
				
				String genero = null;
				Livro livro = null;
				
				if (janela instanceof CadastroLivroLiteratura || janela instanceof CadastroLivroDesenvolvimentoPessoal) {
					String autoresString = null;
					ArrayList<String> autores = null;
					
					if (janela instanceof CadastroLivroLiteratura) {
						CadastroLivroLiteratura janelaL = (CadastroLivroLiteratura) janela;
						autoresString = janelaL.getJaAutores().getText();
						genero = (String) janelaL.getGenerosLiteratura().getSelectedItem();
						
						autores = new ArrayList<>(Arrays.asList(autoresString.split(", ")));
						livro = new LivroLiteratura(titulo, resumo, idioma, editora, genero, ano, unidades, preco, autores);
						
					} else {
						CadastroLivroDesenvolvimentoPessoal janelaD = (CadastroLivroDesenvolvimentoPessoal) janela;
						autoresString = janelaD.getJtAutores().getText();
						genero = (String) janelaD.getGenerosDesenvolvimento().getSelectedItem();
						
						autores = new ArrayList<>(Arrays.asList(autoresString.split(", ")));
						livro = new LivroDesenvolvimentoPessoal(titulo, resumo, idioma, editora, genero, ano, unidades, preco, autores);
					}
					
				} else if (janela instanceof CadastroLivroPeriodico) {
					CadastroLivroPeriodico janelaP = (CadastroLivroPeriodico) janela;
					genero = (String) janelaP.getGenerosPeriodicos().getSelectedItem();
					
					try {
						int mesDeLancamento = Integer.parseInt(janelaP.getJfMesDeLancamento().getText());
						int numeroDeEdicao = Integer.parseInt(janelaP.getJfNumeroDaEdicao().getText());
						
						livro = new LivroPeriodico(titulo, resumo, idioma, editora, genero, ano, unidades, preco, mesDeLancamento, numeroDeEdicao);
						
					} catch (Exception erroFormatPeriodico) {
						JOptionPane.showMessageDialog(janela, "Os campos de mês de lançamento e número da edição devem ser números!", "Erro", JOptionPane.ERROR_MESSAGE, null);
					}
					
				} else if (janela instanceof CadastroLivroTecnico) {
					CadastroLivroTecnico janelaT = (CadastroLivroTecnico) janela;
					genero = (String) janelaT.getGenerosTecnicos().getSelectedItem();
					
					String assunto = janelaT.getJfAssunto().getText();
					if (assunto.isEmpty()) {
						JOptionPane.showMessageDialog(janela, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE, null);
					} else {
						livro = new LivroTecnico(titulo, resumo, idioma, editora, genero, ano, unidades, preco, assunto);
					}
				}
				
				central.adicionarLivro(livro);
				persistencia.salvarCentral(central, "central.xml");
			
				JOptionPane.showMessageDialog(janela, "Livro cadastrado com sucesso!", "Cadastrar novo livro", JOptionPane.PLAIN_MESSAGE, null);
				janela.dispose();
				new JanelaComListaDeLivros(AtributosProjeto.USUARIO_LOGADO);
				
			} catch (NumberFormatException erroFormat) {
				JOptionPane.showMessageDialog(janela, "Os campos de ano, unidade e preço devem ser números!", "Erro", JOptionPane.ERROR_MESSAGE, null);
			}
		}
	}
}

package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import javax.swing.JOptionPane;

import janelas.CadastroLivroDesenvolvimentoPessoal;
import janelas.CadastroLivroLiteratura;
import janelas.CadastroLivroPeriodico;
import janelas.CadastroLivroTecnico;
import janelas.JanelaCadastroLivro;
import janelas.JanelaComDetalhesDoLivro;
import modelo.*;
import modelo.atributos.estaticos.AtributosProjeto;

public class OuvinteDoBotaoSalvarEdicaoLivro implements ActionListener {
	private Livro livro;
	private JanelaCadastroLivro janela;

	public OuvinteDoBotaoSalvarEdicaoLivro(JanelaCadastroLivro janela, Livro livro) {
		this.janela = janela;
		this.livro = livro;
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
				
				livro.setTitulo(titulo);
				livro.setResumo(resumo);
				livro.setIdioma(idioma);
				livro.setEditora(editora);
				livro.setAnoDePublicacao(ano);
				livro.setUnidades(unidades);
				livro.setPreco(preco);
				
				
				if (janela instanceof CadastroLivroLiteratura || janela instanceof CadastroLivroDesenvolvimentoPessoal) {
					String autoresString = null;
					ArrayList<String> autores = null;
					
					if (janela instanceof CadastroLivroLiteratura) {
						CadastroLivroLiteratura janelaL = (CadastroLivroLiteratura) janela;
						autoresString = janelaL.getJaAutores().getText();
						autores = new ArrayList<>(Arrays.asList(autoresString.split(", ")));
						
						LivroLiteratura livroL = (LivroLiteratura) livro;
						livroL.setAutores(autores);
						
					} else {
						CadastroLivroDesenvolvimentoPessoal janelaD = (CadastroLivroDesenvolvimentoPessoal) janela;
						autoresString = janelaD.getJtAutores().getText();
						autores = new ArrayList<>(Arrays.asList(autoresString.split(", ")));
						
						LivroDesenvolvimentoPessoal livroL = (LivroDesenvolvimentoPessoal) livro;
						livroL.setAutores(autores);
					}
					
				} else if (janela instanceof CadastroLivroPeriodico) {
					CadastroLivroPeriodico janelaP = (CadastroLivroPeriodico) janela;
					
					try {
						int mesDeLancamento = Integer.parseInt(janelaP.getJfMesDeLancamento().getText());
						int numeroDeEdicao = Integer.parseInt(janelaP.getJfNumeroDaEdicao().getText());

						LivroPeriodico livroP = (LivroPeriodico) livro;
						livroP.setMesDeLancamento(mesDeLancamento);
						livroP.setNumeroDaEdicao(numeroDeEdicao);
						
					} catch (Exception erroFormatPeriodico) {
						JOptionPane.showMessageDialog(janela, "Os campos de mês de lançamento e número da edição devem ser números!", "Erro", JOptionPane.ERROR_MESSAGE, null);
					}
					
				} else if (janela instanceof CadastroLivroTecnico) {
					CadastroLivroTecnico janelaT = (CadastroLivroTecnico) janela;
					
					String assunto = janelaT.getJfAssunto().getText();
					if (assunto.isEmpty()) {
						JOptionPane.showMessageDialog(janela, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE, null);
					} else {
						LivroTecnico livroT = (LivroTecnico) livro;
						livroT.setAssunto(assunto);
					}
				}
			
				JOptionPane.showMessageDialog(janela, "Livro editado com sucesso!", "Editar livro", JOptionPane.PLAIN_MESSAGE, null);
				
				// Avisando os clientes se tiverem novas unidades do livro disponíveis
				HashSet<String> clientesInteressados = livro.getEmailsDosClientesInteressados();
				if (livro.getUnidades() > 0 && clientesInteressados.size() > 0) {
					for (String emailCliente: clientesInteressados) {
						Mensageiro.enviarAvisoDeLivroDisponivel(emailCliente, livro);
						clientesInteressados.remove(emailCliente);
					}
				}
				
				persistencia.salvarCentral(central, "central.xml");
				
				janela.dispose();
				new JanelaComDetalhesDoLivro(livro, AtributosProjeto.USUARIO_LOGADO);
				
			} catch (NumberFormatException erroFormat) {
				JOptionPane.showMessageDialog(janela, "Os campos de ano, unidade e preço devem ser números!", "Erro", JOptionPane.ERROR_MESSAGE, null);
			}
		}
	}
}


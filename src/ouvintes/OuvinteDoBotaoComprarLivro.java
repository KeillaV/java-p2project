package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import janelas.JanelaComDetalhesDoLivro;
import modelo.*;
import modelo.atributos.estaticos.AtributosProjeto;


public class OuvinteDoBotaoComprarLivro implements ActionListener {
	private Livro livro;
	private JFrame janela;

	public OuvinteDoBotaoComprarLivro(JFrame janela, Livro livro) {
		this.janela = janela;
		this.livro = livro;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		CentralDeInformacoes central = CentralDeInformacoes.getInstance();
		Persistencia persistencia = new Persistencia();
		Cliente usuario;
		
		try {
			usuario = (Cliente) central.recuperarUsuario(AtributosProjeto.USUARIO_LOGADO.getEmail());
			HashMap<Livro, String> colecaoDosLivros = usuario.getColecaoDeLivros().getLivrosDaColecao();
			
			JOptionPane.showMessageDialog(janela,"Pagamento efetuado com sucesso! Livro comprado", "Boleto", JOptionPane.PLAIN_MESSAGE, null);
			
			int unidades = livro.getUnidades() - 1;
			livro.setUnidades(unidades);
			
			
			if (!colecaoDosLivros.containsKey(livro)) {
				usuario.getColecaoDeLivros().adicionarLivro(livro);
				JOptionPane.showMessageDialog(janela,"O livro será adicionado a sua coleção!", "Aviso", JOptionPane.PLAIN_MESSAGE, null);
				
			 } else {
				 JOptionPane.showMessageDialog(janela,"O livro comprado já está na sua coleção!", "Aviso", JOptionPane.PLAIN_MESSAGE, null); 
			 }
			
			persistencia.salvarCentral(central, "central.xml");
			
			janela.dispose();
			new JanelaComDetalhesDoLivro(livro, usuario);
		 
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(janela,"Erro na Compra", "Boleto", JOptionPane.ERROR_MESSAGE, null);
		}
		
	}

}



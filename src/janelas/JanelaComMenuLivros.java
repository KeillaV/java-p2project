package janelas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import modelo.atributos.estaticos.AtributosProjeto;

public abstract class JanelaComMenuLivros extends JFrame {

	public JanelaComMenuLivros() {
		setSize(1000, 1000);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		adicionarMenu();
	}
	
	private class OuvinteDoMenuLivros implements ActionListener {
		private JFrame janela;
		
		public OuvinteDoMenuLivros(JFrame janela) {
			this.janela = janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			String opcao = e.getActionCommand();
			
			switch (opcao) {
				case "Voltar para tela principal":
					if (!(janela instanceof JanelaComListaDeLivros)) {
						janela.dispose();
						new JanelaComListaDeLivros(AtributosProjeto.USUARIO_LOGADO);
					}
					
					break;
				
				case "Deslogar":
					int resposta = JOptionPane.showConfirmDialog(janela, "Tem certeza que deseja sair da conta?", "Sair da conta", JOptionPane.YES_NO_OPTION);
					
					if (resposta == JOptionPane.YES_OPTION) {
						janela.dispose();
						AtributosProjeto.USUARIO_LOGADO = null;
						new JanelaLogin();
					}
					
					break;
			}
		}
	}
	
	private void adicionarMenu() {
		OuvinteDoMenuLivros ouvinte = new OuvinteDoMenuLivros(this);
		
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		
		JMenu opcoesMenu = new JMenu("Opções");
		opcoesMenu.setMnemonic('o');
		menu.add(opcoesMenu);
		
		JMenuItem itemVoltar = new JMenuItem("Voltar para tela principal");
		itemVoltar.setMnemonic('v');
		itemVoltar.addActionListener(ouvinte);
		opcoesMenu.add(itemVoltar);
		
		JMenuItem itemDeslogar = new JMenuItem("Deslogar");
		itemDeslogar.setMnemonic('d');
		itemDeslogar.addActionListener(ouvinte);
		opcoesMenu.add(itemDeslogar);
	}
}


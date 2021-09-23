package janelas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public abstract class JanelaComMenu extends JFrame {

	public JanelaComMenu() {
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adicionarMenu();
	}
	
	private class OuvinteDoMenu implements ActionListener {
		private JFrame janela;
		
		public OuvinteDoMenu(JFrame janela) {
			this.janela = janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			String opcao = e.getActionCommand();
			
			switch (opcao) {
			case "Cadastro":
				if (janela instanceof JanelaLogin) {
					dispose();
					new JanelaCadastroCliente();
				}
				
				break;
				
			case "Login":
				if (janela instanceof JanelaCadastroCliente) {
					dispose();
					new JanelaLogin();
				}
				
				break;
				
			case "Sair":
				dispose();
				break;
			}
		}
	}
	
	private void adicionarMenu() {
		OuvinteDoMenu ouvinte = new OuvinteDoMenu(this);
		
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		
		JMenu opcoesMenu = new JMenu("Opções");
		opcoesMenu.setMnemonic('o');
		menu.add(opcoesMenu);
		
		JMenuItem itemCadastro = new JMenuItem("Cadastro");
		itemCadastro.setMnemonic('c');
		itemCadastro.addActionListener(ouvinte);
		opcoesMenu.add(itemCadastro);
		
		JMenuItem itemLogin = new JMenuItem("Login");
		itemLogin.setMnemonic('l');
		itemLogin.addActionListener(ouvinte);
		opcoesMenu.add(itemLogin);
		
		JMenuItem itemSair = new JMenuItem("Sair");
		itemSair.setMnemonic('s');
		itemSair.addActionListener(ouvinte);
		opcoesMenu.add(itemSair);
		
	}
}

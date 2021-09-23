package janelas;

import ouvintes.OuvinteDoBotaoCadastrarLivreiro;

public class JanelaCadastroLivreiro extends JanelaCadastro {

	public JanelaCadastroLivreiro() {
		super();
		setVisible(true);
	}
	
	public void adicionarOuvinteDoBotaoCadastrar() {
		OuvinteDoBotaoCadastrarLivreiro ouvinte = new OuvinteDoBotaoCadastrarLivreiro(this);
		getBtCadastrar().addActionListener(ouvinte);
	}

}

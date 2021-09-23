package janelas;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import ouvintes.OuvinteDoBotaoCadastrarCliente;


public class JanelaCadastroCliente extends JanelaCadastro {
	
	private JFormattedTextField ftDataDeNascimento;
	private JComboBox<String> cbSexos;
	private JList<String> listaGeneros;
	
	public JanelaCadastroCliente() {
		super();
		adicionarLabels();
		adicionarTextFields();
		adicionarCaixasDeSelecao();
		setVisible(true);
	}
	
	public void adicionarOuvinteDoBotaoCadastrar() {
		OuvinteDoBotaoCadastrarCliente ouvinte = new OuvinteDoBotaoCadastrarCliente(this);
		getBtCadastrar().addActionListener(ouvinte);
	}
	
	
	private void adicionarCaixasDeSelecao() {
		String[] sexos = {"Masculino", "Feminino"};
		cbSexos = new JComboBox<String>(sexos);

		cbSexos.setBounds(430, 120, 115, 30);
		cbSexos.setBackground(Color.WHITE);
		cbSexos.setFont(getFonte());
		
		add(cbSexos);
		
		String[] todosOsGeneros = {"Literatura brasileira", "Literatura estrangeira", 
				"Infanto juvenil", "Artes", "Biografia", "Poesia", "Gibi", "Revista de notícias",
				"Autoajuda", "Religião", "Saúde", "Paradidático", "Formação Profissional"};
		
		listaGeneros = new JList<String>(todosOsGeneros);
		listaGeneros.setVisibleRowCount(2);
		listaGeneros.setToolTipText("Por favor, selecione três gêneros");
		listaGeneros.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		JScrollPane painelGeneros = new JScrollPane(listaGeneros);
		painelGeneros.setBounds(175, 220, 155, 50);
		
		add(painelGeneros);	
	}
	
	private void adicionarLabels() {
		JLabel lbSexo = new JLabel("Sexo:");
		lbSexo.setBounds(380, 120, 40, 30);
		lbSexo.setFont(super.getFonte());
		add(lbSexo);
		
		JLabel lbData = new JLabel("Data de Nascimento:");
		lbData.setBounds(30, 170, 150, 30);
		lbData.setFont(super.getFonte());
		add(lbData);
		
		JLabel lbGeneros = new JLabel("Gêneros favoritos:");
		lbGeneros.setBounds(30, 220, 130, 30);
		lbGeneros.setFont(super.getFonte());
		add(lbGeneros);
	}
	
	
	private void adicionarTextFields() {
		try {
			MaskFormatter mascaraDaData = new MaskFormatter("##/##/####");
			ftDataDeNascimento = new JFormattedTextField(mascaraDaData);
			
			ftDataDeNascimento.setBounds(190, 170, 120, 30);
			ftDataDeNascimento.setHorizontalAlignment(JLabel.CENTER);
			
			add(ftDataDeNascimento);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public JFormattedTextField getFtDataDeNascimento() {
		return ftDataDeNascimento;
	}

	public JComboBox<String> getCbSexos() {
		return cbSexos;
	}

	public JList<String> getListaGeneros() {
		return listaGeneros;
	}
}


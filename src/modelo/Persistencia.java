package modelo;



import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Persistencia {
	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
	
	public void salvarCentral(CentralDeInformacoes central, String nomeArquivo) {
		File arquivo = new File(nomeArquivo);
		String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>\n";
		 xml += xstream.toXML(central);
		
		try {
			arquivo.createNewFile();
			PrintWriter salvar = new PrintWriter(arquivo);
			salvar.print(xml);
			salvar.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public CentralDeInformacoes recuperarCentral(String nomeArquivo)  {
		File arquivo = new File(nomeArquivo);
		try { 
			if (arquivo.exists()) {
				FileInputStream fis = new FileInputStream(arquivo);
				CentralDeInformacoes central =(CentralDeInformacoes) xstream.fromXML(fis);
				return central;
			}
			
		} catch(Exception e) {
			e.printStackTrace();	
		}  
		
		return new CentralDeInformacoes();
	}
	
}

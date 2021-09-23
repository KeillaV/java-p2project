package modelo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorDeRelatorios {

	public static void gerarRelatorio(List<Livro> todosOsLivros) {
		Document doc = new Document(PageSize.A4, 54, 54, 54, 54);

		try {
			OutputStream os = new FileOutputStream("relatório.pdf");
			PdfWriter.getInstance(doc, os);
			
			doc.open();
			
			Font fonteTitulo = new Font(FontFamily.UNDEFINED, 20, Font.BOLD);
			
			Paragraph titulo = new Paragraph("Estante Virtual", fonteTitulo);
			titulo.setAlignment(Element.ALIGN_CENTER);
			doc.add(titulo);
			
			Paragraph subtitulo = new Paragraph("Livros cadastrados no sistema", fonteTitulo);
			subtitulo.setAlignment(Element.ALIGN_CENTER);
			subtitulo.setSpacingAfter(40);
			doc.add(subtitulo);
			
			todosOsLivros.sort(new Comparator<Livro>() {
				public int compare(Livro livro1, Livro livro2) {
					if (livro1.getNumeroDeVisualizacoes() < livro2.getNumeroDeVisualizacoes()) {
						return 1;
					} else if (livro1.getNumeroDeVisualizacoes() > livro2.getNumeroDeVisualizacoes()) {
						return -1;
					}
					
					return 0;
				}
			});
			
			for (Livro livro: todosOsLivros) {
				// String tipo = livro.getClass().getSimpleName(); -> Retorna nome literal da classe
				
				Paragraph id = new Paragraph("ID: " + livro.getId());
				Paragraph tituloLivro = new Paragraph("Título: " + livro.getTitulo());
				Paragraph resumo = new Paragraph("Resumo: " + livro.getResumo());
				Paragraph idioma = new Paragraph("Idioma: " + livro.getIdioma());
				Paragraph editora = new Paragraph("Editora: " + livro.getEditora());
				Paragraph ano = new Paragraph("Ano de publicação: " + livro.getAnoDePublicacao());
				Paragraph tipoLivro = new Paragraph("Tipo: " + livro.getTipo());
				Paragraph visualizacoes = new Paragraph("Número de visualizações: " + livro.getNumeroDeVisualizacoes());
				visualizacoes.setSpacingAfter(30);
				
				doc.add(id);
				doc.add(tituloLivro);
				doc.add(resumo);
				doc.add(idioma);
				doc.add(editora);
				doc.add(ano);
				doc.add(tipoLivro);
				doc.add(visualizacoes);
			}
			
			doc.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static void gerarRelatorioLivrosEsgotados(List<Livro> todosOsLivros) {
		Document doc = new Document(PageSize.A4, 54, 54, 54, 54);

		try {
			OutputStream os = new FileOutputStream("relatório.pdf");
			PdfWriter.getInstance(doc, os);
			
			doc.open();
			Font fonteTitulo = new Font(FontFamily.UNDEFINED, 20, Font.BOLD);
			
			Paragraph titulo = new Paragraph("Estante Virtual", fonteTitulo);
			titulo.setAlignment(Element.ALIGN_CENTER);
			doc.add(titulo);
			
			Paragraph subtitulo = new Paragraph("Livros esgotados com mais clientes interessados", fonteTitulo);
			subtitulo.setAlignment(Element.ALIGN_CENTER);
			subtitulo.setSpacingAfter(40);
			doc.add(subtitulo);

			todosOsLivros.sort(new Comparator<Livro>() {
				public int compare(Livro livro1, Livro livro2) {
					if (livro1.getEmailsDosClientesInteressados().size() < livro2.getEmailsDosClientesInteressados().size()) {
						return 1;
					} else if (livro1.getEmailsDosClientesInteressados().size() < livro2.getEmailsDosClientesInteressados().size()) {
						return -1;
					}
					return 0;
				}
			});			
			
			doc.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
}

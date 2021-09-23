package modelo;

import java.util.ArrayList;
import java.util.Scanner;



public class ProgramaListaDeAquecimento {
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		CentralDeInformacoes central = CentralDeInformacoes.getInstance();
		

		System.out.println("\n1 - Novo livro "
				+ "\n2 - Listar livros "
				+ "\n3 - Gerar relat�rio em PDF com livros cadastrados"
				+ "\n4 - Enviar relat�rio por e-mail"
				+ "\nS - Sair");
		
		System.out.print("\nDigite a op��o desejada: ");
		String opcao = leitor.nextLine();

		while (!opcao.toUpperCase().equals("S")) {
			if (opcao.equals("1")) {
				System.out.println("\nTipos dispon�veis: \nLiteratura \nT�cnico \nPeri�dico \nDesenvolvimento Pessoal");
				System.out.print("\nDigite o tipo do livro: ");
				String tipo = leitor.nextLine().toLowerCase();

				System.out.print("\nDigite o t�tulo: ");
				String titulo = leitor.nextLine();
				System.out.print("Digite o resumo: ");
				String resumo = leitor.nextLine();
				System.out.print("Digite o idioma: ");
				String idioma = leitor.nextLine();
				System.out.print("Digite a editora: ");
				String editora = leitor.nextLine();
				System.out.print("Digite o g�nero: ");
				String genero = leitor.nextLine();
				System.out.print("Digite o ano de publica��o: ");
				int anoDePublicacao = Integer.parseInt(leitor.nextLine());
				System.out.print("Digite a quantidade de unidades dispon�veis: ");
				int unidades = Integer.parseInt(leitor.nextLine());
				System.out.print("Digite o pre�o do livro: ");
				float preco = Float.parseFloat(leitor.nextLine());
				

				Livro livro = null;
				
				if (tipo.equals("literatura") || tipo.equals("desenvolvimento pessoal")) {
					ArrayList<String> autores = new ArrayList<>();
					
					boolean continuar = true;
					
					do {
						System.out.print("Digite o nome do autor: ");
						String autor = leitor.nextLine();
						autores.add(autor);
						
						System.out.println("\n1 - Continuar cadastrando autores \n2 - Encerrar cadastro");
						String opcaoContinuar = leitor.nextLine();
						
						if (opcaoContinuar.equals("2")) {
							continuar = false;
						}
						
					} while (continuar == true);
					
					if (tipo.equals("literatura")) {
						livro = new LivroLiteratura(titulo, resumo, idioma, editora, genero, anoDePublicacao, unidades, preco, autores);
						
					} else {
						livro = new LivroDesenvolvimentoPessoal(titulo, resumo, idioma, editora, genero, anoDePublicacao, unidades, preco, autores);
					}
					
				} else if (tipo.equals("t�cnico")) {
					System.out.print("Digite o assunto: ");
					String assunto = leitor.nextLine();
					
					livro = new LivroTecnico(titulo, resumo, idioma, editora, genero, anoDePublicacao, unidades, preco, assunto);
					
				} else if (tipo.equals("peri�dico")) {
					System.out.print("Digite o m�s de lan�amento: ");
					int mesDeLancamento = Integer.parseInt(leitor.nextLine());
					
					System.out.print("Digite o n�mero da edi��o: ");
					int numeroDaEdicao = Integer.parseInt(leitor.nextLine());
					
					livro = new LivroPeriodico(titulo, resumo, idioma, editora, genero, anoDePublicacao, unidades, preco, mesDeLancamento, numeroDaEdicao);
				}

				if (central.adicionarLivro(livro)) {
					System.out.println("\nLivro cadastrado! \n");
				} else {
					System.out.println("\nO livro informado j� est� cadastrado. \n");
				}

			} else if (opcao.equals("2")) {
				System.out.println("\nLivros listados: ");
				if (central.getTodosOsLivros().size() == 0) {
					System.out.println("N�o h� livros cadastrados!");
				} else {
					for (int indice = 0; indice < central.getTodosOsLivros().size(); indice++) {
						System.out.println(central.getTodosOsLivros().get(indice));
					}
				}
			} else if (opcao.equals("3")) {
				if (central.getTodosOsLivros().size() == 0) {
					System.out.println("N�o h� livros cadastrados!");
				} else {
					GeradorDeRelatorios.gerarRelatorio(central.getTodosOsLivros());
					System.out.println("Relat�rio gerado!");
				}
				
			} else if (opcao.equals("4")) {
				System.out.print("Digite o seu endere�o de e-mail: ");
				String endereco = leitor.nextLine();
				
				Mensageiro.enviarListaDeLivros(endereco);
				System.out.println("E-mail enviado!");  
			}

			System.out.println("\n1 - Novo livro "
					+ "\n2 - Listar livros "
					+ "\n3 - Gerar relat�rio em PDF com livros cadastrados"
					+ "\n4 - Enviar relat�rio por e-mail"
					+ "\nS - Sair");
			System.out.print("\nDigite a op��o desejada: ");
			opcao = leitor.nextLine();
		}

		leitor.close();
	}
}

package Dicionario;

import java.util.List;
import java.util.Scanner;

public class MenuTerminal {
    private GerenciadorDicionario gerenciador;
    private Scanner sc;

    public MenuTerminal(GerenciadorDicionario gerenciador) {
        this.gerenciador = gerenciador;
        this.sc = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("---- DICIONÁRIO ----");
            System.out.println("1. Adicionar Categoria");
            System.out.println("2. Adicionar Palavra");
            System.out.println("3. Buscar Palavra");
            System.out.println("4. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> adicionarCategoria();
                case 2 -> adicionarPalavra();
                case 3 -> buscarPalavra();
                case 4 -> System.out.println("Saindo.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 4);
    }

    private void adicionarCategoria() {
        System.out.print("Nome da Categoria: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String desc = sc.nextLine();

        gerenciador.adicionarCategoria(nome, desc);
        System.out.println("Categoria adicionada.");
    }

    private void adicionarPalavra() {
        System.out.print("Termo: ");
        String termo = sc.nextLine();

        System.out.print("Categoria: ");
        String categoria = sc.nextLine();

        System.out.print("Significado: ");
        String significado = sc.nextLine();

        System.out.print("Tradução: ");
        String traducao = sc.nextLine();

        System.out.print("Quantos exemplos? ");
        int qtd = sc.nextInt();
        sc.nextLine();
        String[] exemplos = new String[qtd];
        for (int i = 0; i < qtd; i++) {
            System.out.print("Exemplo " + (i + 1) + ": ");
            exemplos[i] = sc.nextLine();
        }

        //PronunciacaoStrategy pronuncia = escolherPronuncia();

        boolean sucesso = gerenciador.adicionarPalavra(termo, categoria, significado, traducao, exemplos, pronuncia);
        if (sucesso) {
            System.out.println("Palavra adicionada.");
        } else {
            System.out.println("Categoria não encontrada.");
        }
    }

    /*
    private PronunciacaoStrategy escolherPronuncia() {
        System.out.println("Escolha a pronúncia:");
        System.out.println("1- ");
        System.out.println("2- ");
        System.out.print("Opção: ");
        int escolha = sc.nextInt();
        sc.nextLine();

        return switch (escolha) {
            case 1 -> 
            case 2 -> new PronunciaBritanica();
            default -> {
                System.out.println("Padrão Americana.");
                yield new PronunciaAmericana();
            }
        };
    }
 */
    private void buscarPalavra() {
        System.out.print("Digite a categoria: ");
        String nomeCategoria = sc.nextLine();

        Categoria cat = gerenciador.buscarCategoria(nomeCategoria);
        if (cat == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        System.out.print("Digite o termo da palavra: ");
        String termo = sc.nextLine();

        Palavra palavra = cat.buscarPalavra(termo);
        if (palavra == null) {
            System.out.println("Palavra não encontrada.");
            return;
        }

        System.out.println("\n" + palavra);
        PronunciacaoStrategy novaPronuncia = escolherPronuncia();
        palavra.setPronunciacao(novaPronuncia);
        System.out.println(palavra.obterPronuncia());
    }
}



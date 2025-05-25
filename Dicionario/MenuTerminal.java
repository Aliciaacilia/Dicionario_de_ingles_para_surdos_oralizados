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
            System.out.println("1- Adicionar Categoria");
            System.out.println("2- Adicionar Palavra");
            System.out.println("3- Buscar Palavra");
            System.out.println("4- Meus dicionários");
            System.out.println("5- Selecionar dicionário");
            System.out.println("6- Criar dicionário personalizado");
            System.out.println("7- Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> adicionarCategoria();
                case 2 -> adicionarPalavra();
                case 3 -> buscarPalavra();
                case 4 -> listarDicionarios();
                case 5 -> selecionarDicionario();
                case 6 -> criarDicionario();
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 7);
    }

    private void adicionarCategoria() {
        if (gerenciador.getDicionarioAtual() == null) {
            System.out.println("Nenhum dicionário selecionado. Crie ou selecione um primeiro.");
            return;
        }

        System.out.print("Nome da Categoria: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String desc = sc.nextLine();

        gerenciador.adicionarCategoria(nome, desc);
        System.out.println("Categoria adicionada.");
    }

    private void adicionarPalavra() {
        if (gerenciador.getDicionarioAtual() == null) {
    System.out.println("Nenhum dicionário selecionado. Crie ou selecione um primeiro.");
    return;
    }
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

        PronunciacaoStrategy pronuncia = escolherPronuncia();

        boolean sucesso = gerenciador.adicionarPalavra(termo, categoria, significado, traducao, exemplos, pronuncia);
        if (sucesso) {
            System.out.println("Palavra adicionada.");
        } else {
            System.out.println("Categoria não encontrada.");
        }
    }

    private PronunciacaoStrategy escolherPronuncia() {
    System.out.println("Escolha a pronúncia:");
    System.out.println("1- Pronúncia simples (fonética)");
    System.out.print("Opção: ");
    int escolha = sc.nextInt();
    sc.nextLine();

    return switch (escolha) {
        case 1 -> new PronunciaFonetica();
        default -> new PronunciaFonetica();
        };
    }

    private void buscarPalavra() {
    if (gerenciador.getDicionarioAtual() == null) {
        System.out.println("Nenhum dicionário selecionado. Crie ou selecione um primeiro.");
        return;
    }

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
        palavra.obterPronuncia();
    }

        private void criarDicionario() {
        System.out.print("Nome do novo dicionário: ");
        String nome = sc.nextLine();
        boolean sucesso = gerenciador.criarDicionario(nome);
        if (sucesso) {
            System.out.println("Dicionário '" + nome + "' criado e selecionado.");
        } else {
            System.out.println("Já existe um dicionário com esse nome.");
        }
    }

        private void listarDicionarios() {
    List<String> nomes = gerenciador.listarDicionarios();
    if (nomes.isEmpty()) {
        System.out.println("Nenhum dicionário criado.");
        return;
    }

    System.out.println("Seus dicionários:");
    for (int i = 0; i < nomes.size(); i++) {
        System.out.println((i + 1) + " - " + nomes.get(i));
    }

    System.out.print("Digite o número do dicionário para acessar ou 0 para voltar: ");
    int escolha = sc.nextInt();
    sc.nextLine();

    if (escolha == 0) return;

    if (escolha > 0 && escolha <= nomes.size()) {
        String nomeSelecionado = nomes.get(escolha - 1);
        gerenciador.selecionarDicionario(nomeSelecionado);
        System.out.println("Dicionário '" + nomeSelecionado + "' selecionado.");
    } else {
        System.out.println("Opção inválida.");
    }
    }

    private void selecionarDicionario() {
    List<String> nomesDicionarios = gerenciador.listarNomesDicionarios();

    if (nomesDicionarios.isEmpty()) {
        System.out.println("Nenhum dicionário criado ainda.");
        return;
    }

    System.out.println("Dicionários disponíveis:");
    for (int i = 0; i < nomesDicionarios.size(); i++) {
        System.out.println((i + 1) + "- " + nomesDicionarios.get(i));
    }

    System.out.print("Escolha o número do dicionário: ");
    int escolha = sc.nextInt();
    sc.nextLine();

    if (escolha < 1 || escolha > nomesDicionarios.size()) {
        System.out.println("Escolha inválida.");
        return;
    }

    String nomeSelecionado = nomesDicionarios.get(escolha - 1);
    boolean sucesso = gerenciador.selecionarDicionario(nomeSelecionado);

    if (sucesso) {
        System.out.println("Dicionário \"" + nomeSelecionado + "\" selecionado com sucesso.");
    } else {
        System.out.println("Erro ao selecionar o dicionário.");
    }
}

}


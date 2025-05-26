package Menu;

import java.util.List;
import java.util.Scanner;
import Dicionario.Categoria;
import Dicionario.GerenciadorDicionario;
import Dicionario.Palavra;
import Dicionario.PronunciaFonetica;
import Dicionario.PronunciaStrategy;
import Dicionario.PronunciaTextoSimples;
import Dicionario.utils.ConsoleColors;
import Dicionario.data.*;

public class MenuTerminal {
    private GerenciadorDicionario gerenciador;
    private Scanner sc;

    public MenuTerminal(GerenciadorDicionario gerenciador) {
        this.gerenciador = gerenciador;
        this.sc = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao = -1;
            do {
            System.out.println ("");
            System.out.println(ConsoleColors.BLUE_DARK + "---- DSO - Dicionário de Surdos Oralizados ----" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN + "1" + ConsoleColors.RESET + " - " + ConsoleColors.WHITE + "Adicionar Categoria" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN + "2" + ConsoleColors.RESET + " - " + ConsoleColors.WHITE + "Adicionar Palavra" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN + "3" + ConsoleColors.RESET + " - " + ConsoleColors.WHITE + "Buscar Palavra" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN + "4" + ConsoleColors.RESET + " - " + ConsoleColors.WHITE + "Meus dicionários" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN + "5" + ConsoleColors.RESET + " - " + ConsoleColors.WHITE + "Selecionar dicionário" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN + "6" + ConsoleColors.RESET + " - " + ConsoleColors.WHITE + "Criar dicionário personalizado" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN + "7" + ConsoleColors.RESET + " - " + ConsoleColors.WHITE + "Sair" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.PURPLE + "Escolha: " + ConsoleColors.RESET);

            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED + "Por favor, digite um número válido." + ConsoleColors.RESET);
                continue;
            }

            System.out.println();

            switch (opcao) {
                case 1 -> adicionarCategoria();
                case 2 -> adicionarPalavra();
                case 3 -> buscarPalavra();
                case 4 -> listarDicionarios();
                case 5 -> selecionarDicionario();
                case 6 -> criarDicionario();
                case 7 -> System.out.println(ConsoleColors.GREEN + "Saindo... Até logo!" + ConsoleColors.RESET);
                default -> System.out.println(ConsoleColors.RED + "Opção inválida." + ConsoleColors.RESET);
            }

            System.out.println();

        } while (opcao != 7);
    }

    private void adicionarCategoria() {
        if (gerenciador.getDicionarioAtual() == null) {
            System.out.println(ConsoleColors.RED + "Nenhum dicionário selecionado. Crie ou selecione um primeiro." + ConsoleColors.RESET);
            return;
        }

        System.out.print("Nome da Categoria: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String desc = sc.nextLine();

        gerenciador.adicionarCategoria(nome, desc);
        System.out.println(ConsoleColors.GREEN + "Categoria adicionada." + ConsoleColors.RESET);
    }

    private void adicionarPalavra() {
        if (gerenciador.getDicionarioAtual() == null) {
            System.out.println(ConsoleColors.RED + "Nenhum dicionário selecionado. Crie ou selecione um primeiro." + ConsoleColors.RESET);
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
        int qtd = 0;
        try {
            qtd = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(ConsoleColors.RED + "Número inválido. Voltando ao menu." + ConsoleColors.RESET);
            return;
        }
        if (qtd < 0) qtd = 0;

        String[] exemplos = new String[qtd];
        for (int i = 0; i < qtd; i++) {
            System.out.print("Exemplo " + (i + 1) + ": ");
            exemplos[i] = sc.nextLine();
        }

        PronunciaStrategy pronuncia = escolherPronuncia();

        boolean sucesso = gerenciador.adicionarPalavra(termo, categoria, significado, traducao, exemplos, pronuncia);
        if (sucesso) {
            System.out.println(ConsoleColors.GREEN + "Palavra adicionada." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + "Categoria não encontrada." + ConsoleColors.RESET);
        }
    }

    private PronunciaStrategy escolherPronuncia() {
        System.out.println("Escolha o tipo de pronúncia:");
        System.out.println("1- Pronúncia textual simples (silábica)");
        System.out.println("2- Pronúncia fonética (IPA)");
        System.out.print("Opção: ");
        int escolha = 1;
        try {
            escolha = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(ConsoleColors.RED + "Opção inválida, usando pronúncia textual simples." + ConsoleColors.RESET);
            return new PronunciaTextoSimples();
        }

        return switch (escolha) {
            case 1 -> new PronunciaTextoSimples();
            case 2 -> new PronunciaFonetica();
            default -> {
                System.out.println(ConsoleColors.RED + "Opção inválida, usando pronúncia textual simples." + ConsoleColors.RESET);
                yield new PronunciaTextoSimples();
            }
        };
    }

    private void buscarPalavra() {
        if (gerenciador.getDicionarioAtual() == null) {
            System.out.println(ConsoleColors.RED + "Nenhum dicionário selecionado. Crie ou selecione um primeiro." + ConsoleColors.RESET);
            return;
        }

        System.out.print("Digite a categoria: ");
        String nomeCategoria = sc.nextLine();

        Categoria cat = gerenciador.buscarCategoria(nomeCategoria);
        if (cat == null) {
            System.out.println(ConsoleColors.RED + "Categoria não encontrada." + ConsoleColors.RESET);
            return;
        }

        System.out.print("Digite o termo da palavra: ");
        String termo = sc.nextLine();

        Palavra palavra = PalavraDAO.buscarPorTermoECategoria(termo, cat.getId());
        if (palavra == null) {
            System.out.println(ConsoleColors.RED + "Palavra não encontrada." + ConsoleColors.RESET);
            return;
        }

        System.out.println("\n" + palavra);
        PronunciaStrategy novaPronuncia = escolherPronuncia();
        palavra.setPronunciacao(novaPronuncia);
        palavra.obterPronuncia();
    }

    private void criarDicionario() {
        System.out.print("Nome do novo dicionário: ");
        String nome = sc.nextLine();
        boolean sucesso = gerenciador.criarDicionario(nome);
        if (sucesso) {
            System.out.println(ConsoleColors.GREEN + "Dicionário '" + nome + "' criado e selecionado." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + "Já existe um dicionário com esse nome." + ConsoleColors.RESET);
        }
    }

    private void listarDicionarios() {
        List<String> nomes = gerenciador.listarDicionarios();
        if (nomes.isEmpty()) {
            System.out.println(ConsoleColors.RED + "Nenhum dicionário criado." + ConsoleColors.RESET);
            return;
        }

        System.out.println("Seus dicionários:");
        for (int i = 0; i < nomes.size(); i++) {
            System.out.println((i + 1) + " - " + nomes.get(i));
        }

        System.out.print("Digite o número do dicionário para acessar ou 0 para voltar: ");
        int escolha = 0;
        try {
            escolha = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(ConsoleColors.RED + "Entrada inválida. Voltando." + ConsoleColors.RESET);
            return;
        }

        if (escolha == 0) return;

        if (escolha > 0 && escolha <= nomes.size()) {
            String nomeSelecionado = nomes.get(escolha - 1);
            gerenciador.selecionarDicionario(nomeSelecionado);
            System.out.println(ConsoleColors.GREEN + "Dicionário '" + nomeSelecionado + "' selecionado." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + "Opção inválida." + ConsoleColors.RESET);
        }
    }

    private void selecionarDicionario() {
        List<String> nomesDicionarios = gerenciador.listarNomesDicionarios();

        if (nomesDicionarios.isEmpty()) {
            System.out.println(ConsoleColors.RED + "Nenhum dicionário criado ainda." + ConsoleColors.RESET);
            return;
        }

        System.out.println("Dicionários disponíveis:");
        for (int i = 0; i < nomesDicionarios.size(); i++) {
            System.out.println((i + 1) + "- " + nomesDicionarios.get(i));
        }

        System.out.print("Escolha o número do dicionário: ");
        int escolha = 0;
        try {
            escolha = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(ConsoleColors.RED + "Escolha inválida." + ConsoleColors.RESET);
            return;
        }

        if (escolha < 1 || escolha > nomesDicionarios.size()) {
            System.out.println(ConsoleColors.RED + "Escolha inválida." + ConsoleColors.RESET);
            return;
        }

        String nomeSelecionado = nomesDicionarios.get(escolha - 1);
        boolean sucesso = gerenciador.selecionarDicionario(nomeSelecionado);

        if (sucesso) {
            System.out.println(ConsoleColors.GREEN + "Dicionário \"" + nomeSelecionado + "\" selecionado com sucesso." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + "Erro ao selecionar o dicionário." + ConsoleColors.RESET);
        }
    }
}

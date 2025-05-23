package Dicionario;

public class Main {
    public static void main(String[] args) {
        DicionarioFactory factory = new DicionarioFactorySimples();
        GerenciadorDicionario gerenciador = new GerenciadorDicionario(factory);
        MenuTerminal menu = new MenuTerminal(gerenciador);

        menu.exibirMenu();
    }
}

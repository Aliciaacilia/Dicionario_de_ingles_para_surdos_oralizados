package Dicionario;

public class Main {
    public static void main(String[] args) {
        DicionarioFactory factory = new DicionarioFactoryPadrao();
        GerenciadorDicionario gerenciador = GerenciadorDicionario.getInstance(factory);
        MenuTerminal menu = new MenuTerminal(gerenciador);

        menu.exibirMenu();
    }
}

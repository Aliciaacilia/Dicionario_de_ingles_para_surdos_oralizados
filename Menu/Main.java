package Menu;

import java.sql.Connection;
import java.sql.SQLException;
import Dicionario.DicionarioFactory;
import Dicionario.DicionarioFactoryPadrao;
import Dicionario.GerenciadorDicionario;
import Dicionario.data.ConexaoPostgres;

public class Main {
    public static void main(String[] args) {
        DicionarioFactory factory = new DicionarioFactoryPadrao();
        GerenciadorDicionario gerenciador = GerenciadorDicionario.getInstance(factory);
        MenuTerminal menu = new MenuTerminal(gerenciador);

        menu.exibirMenu();
    }
}

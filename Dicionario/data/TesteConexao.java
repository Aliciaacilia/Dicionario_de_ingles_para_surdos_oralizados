package Dicionario.data;

import Dicionario.data.ConexaoPostgres;
import java.sql.Connection;

public class TesteConexao {
    public static void main(String[] args) {
        try (Connection con = ConexaoPostgres.conectar()) {
            System.out.println("Conectado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

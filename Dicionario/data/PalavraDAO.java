package Dicionario.data;

import Dicionario.Categoria;
import Dicionario.Palavra;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PalavraDAO {

    public static void inserir(Palavra palavra) throws SQLException {
        String sql = "INSERT INTO palavra (termo, categoria_nome, significado, traducao, exemplos) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexaoPostgres.conectar();
             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pst.setString(1, palavra.getTermo());
            pst.setString(2, palavra.getCategoria().getNome());
            pst.setString(3, palavra.getSignificado());
            pst.setString(4, palavra.getTraducao());
            pst.setString(5, exemplosParaString(palavra.getExemplos()));
            
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    palavra.setId(rs.getInt(1));
                }
            }
        }
    }

    public static Palavra buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM palavra WHERE id = ?";
        try (Connection con = ConexaoPostgres.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return montarPalavra(rs);
                }
            }
        }
        return null;
    }

    public static List<Palavra> listarTodas() throws SQLException {
        List<Palavra> lista = new ArrayList<>();
        String sql = "SELECT * FROM palavra";
        try (Connection con = ConexaoPostgres.conectar();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                lista.add(montarPalavra(rs));
            }
        }
        return lista;
    }

    public static void atualizar(Palavra palavra) throws SQLException {
        String sql = "UPDATE palavra SET termo = ?, categoria_nome = ?, significado = ?, traducao = ?, exemplos = ? WHERE id = ?";
        try (Connection con = ConexaoPostgres.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, palavra.getTermo());
            pst.setString(2, palavra.getCategoria().getNome());
            pst.setString(3, palavra.getSignificado());
            pst.setString(4, palavra.getTraducao());
            pst.setString(5, exemplosParaString(palavra.getExemplos()));
            pst.setInt(6, palavra.getId());
            
            pst.executeUpdate();
        }
    }

    public static void deletar(int id) throws SQLException {
        String sql = "DELETE FROM palavra WHERE id = ?";
        try (Connection con = ConexaoPostgres.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
    private static Palavra montarPalavra(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String termo = rs.getString("termo");
        String categoriaNome = rs.getString("categoria_nome");
        Categoria categoria = new Categoria(categoriaNome, "");
        String significado = rs.getString("significado");
        String traducao = rs.getString("traducao");
        String exemplosStr = rs.getString("exemplos");
        String[] exemplos = stringParaExemplos(exemplosStr);

        return new Palavra(id, termo, categoria, significado, traducao, exemplos, null);
    }

    private static String exemplosParaString(String[] exemplos) {
        if (exemplos == null || exemplos.length == 0) return "";
        return String.join(";", exemplos);
    }

    private static String[] stringParaExemplos(String exemplosStr) {
        if (exemplosStr == null || exemplosStr.isEmpty()) return new String[0];
        return exemplosStr.split(";");
    }

    public static Palavra buscarPorTermoECategoria(String termo, int idCategoria) {
    Palavra palavra = null;
    String sql = "SELECT * FROM palavra WHERE termo = ? AND categoria_id = ?";
    try (Connection conn = ConexaoPostgres.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, termo);
        stmt.setInt(2, idCategoria);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            palavra = montarPalavra(rs);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return palavra;
    }
}

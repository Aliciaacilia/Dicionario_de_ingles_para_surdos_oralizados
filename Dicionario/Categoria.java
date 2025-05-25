package Dicionario;

import java.util.*;

public class Categoria {
    private String nome;
    private String descricao;
    private Map<String, Palavra> palavras;

    public Categoria(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.palavras = new TreeMap<>();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void adicionarPalavra(Palavra palavra) {
        if (palavra != null && palavra.getTermo() != null) {
            palavras.put(palavra.getTermo(), palavra);
        }
    }

    public Palavra buscarPalavra(String termo) {
        return palavras.get(termo);
    }

    public Collection<Palavra> listarPalavras() {
        return palavras.values();
    }
}

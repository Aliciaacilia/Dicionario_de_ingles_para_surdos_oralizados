package Dicionario;

import java.util.*;

public class GerenciadorDicionario {
    private Map<String, Categoria> categorias = new TreeMap<>();
    private DicionarioFactory factory;
    private Map<String, Map<String, Categoria>> dicionarios = new HashMap<>();
    private String dicionarioAtual;

    public GerenciadorDicionario(DicionarioFactory factory) {
        if (factory == null) {
            throw new IllegalArgumentException("Factory não pode ser nula.");
        }
        this.factory = factory;
    }

    public boolean adicionarCategoria(String nome, String descricao) {
    Map<String, Categoria> dic = getDicionarioAtual();
    if (dic == null) return false;
    if (dic.containsKey(nome)) return false;

    dic.put(nome, new Categoria(nome, descricao));
    return true;
    }

    public boolean adicionarPalavra(String termo, String nomeCategoria, String significado,
                                String traducao, String[] exemplos, PronunciacaoStrategy pronuncia) {
    Map<String, Categoria> dic = getDicionarioAtual();
    if (dic == null) return false;

    Categoria cat = dic.get(nomeCategoria);
    if (cat == null) return false;

    Palavra palavra = new Palavra(termo, cat, significado, traducao, exemplos, pronuncia);
    cat.adicionarPalavra(palavra);
    return true;
}

    public Palavra buscarPalavra(String termo) {
        for (Categoria categoria : categorias.values()) {
            Palavra encontrada = categoria.buscarPalavra(termo);
            if (encontrada != null) {
                return encontrada;
            }
        }
        return null;
    }

        public Categoria buscarCategoria(String nomeCategoria) {
        Map<String, Categoria> dic = getDicionarioAtual();
        if (dic == null) return null;
        return dic.get(nomeCategoria);
    }

    public Collection<Categoria> getCategorias() {
        return categorias.values();
    }

        public boolean criarDicionario(String nome) {
        if (dicionarios.containsKey(nome)) return false;
        dicionarios.put(nome, new TreeMap<>());
        dicionarioAtual = nome;
        return true;
    }

    public List<String> listarDicionarios() {
        return new ArrayList<>(dicionarios.keySet());
    }

    public boolean selecionarDicionario(String nome) {
        if (!dicionarios.containsKey(nome)) return false;
        dicionarioAtual = nome;
        return true;
    }

    public Map<String, Categoria> getDicionarioAtual() {
        if (dicionarioAtual == null) return null;
        return dicionarios.get(dicionarioAtual);
    }

    public List<String> listarNomesDicionarios() {
    return new ArrayList<>(dicionarios.keySet());
    }

 }
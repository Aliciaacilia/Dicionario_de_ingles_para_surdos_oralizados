package Dicionario;

import java.util.*;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class GerenciadorDicionario {
    private Map<String, Categoria> categorias = new TreeMap<>();
    private DicionarioFactory factory;

    public GerenciadorDicionario(DicionarioFactory factory) {
        if (factory == null) {
            throw new IllegalArgumentException("Factory não pode ser nula.");
        }
        this.factory = factory;
    }

    public boolean adicionarCategoria(String nome, String descricao) {
        if (categorias.containsKey(nome)) {
            return false;
        }
        Categoria nova = factory.criarCategoria(nome, descricao);
        categorias.put(nome, nova);
        return true;
    }

    public boolean adicionarPalavra(String termo, String nomeCategoria, String significado, String etimologia, String[] sinonimos, PronunciacaoStrategy pronunciacao) {
        Categoria categoria = categorias.get(nomeCategoria);
        if (categoria == null) {
            return false;
        }
        Palavra palavra = factory.criarPalavra(termo, categoria, significado, etimologia, sinonimos, pronunciacao);
        categoria.adicionarPalavra(palavra);
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

    public Categoria buscarCategoria(String nome) {
    return categorias.get(nome);
    }

    public Collection<Categoria> getCategorias() {
        return categorias.values();
    }
 }
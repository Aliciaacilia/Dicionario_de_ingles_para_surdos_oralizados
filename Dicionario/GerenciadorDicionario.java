package Dicionario;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorDicionario {

    private static GerenciadorDicionario gerenciadordicionario;

    private List<Palavra> palavras;
    private List<Categoria> categorias;
    private DicionarioFactory fabricaAtual;

    private GerenciadorDicionario() {
        palavras = new ArrayList<>();
        categorias = new ArrayList<>();
    }

    public static GerenciadorDicionario getInstancia() {
        if (instancia == null) {
            instancia = new GerenciadorDicionario();
        }
        return gerenciadordicionario;
    }

    public void carregarFabrica(DicionarioFactory fabrica) {
        this.fabricaAtual = fabrica;
        this.categorias = fabrica.criarCategoriasIniciais();
        this.palavras.clear();
    }

    public void adicionarPalavra(Palavra palavra) {
        palavras.add(palavra);
    }

    public Palavra buscarPalavra(String texto) {
        for (Palavra p : palavras) {
            if (p.getTexto().equalsIgnoreCase(texto)) {
                return p;
            }
        }
        return null;
    }

    public List<Categoria> listarCategorias() {
        return categorias;
    }

    public void mudarFabrica(DicionarioFactory novaFabrica) {
        carregarFabrica(novaFabrica);
    }
}

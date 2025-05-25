package Dicionario;

public interface PalavraFactory {
    Palavra criarPalavra(String termo, Categoria categoria, String significado, String traducao, String[] exemplos, PronunciacaoStrategy pronunciacao);
}

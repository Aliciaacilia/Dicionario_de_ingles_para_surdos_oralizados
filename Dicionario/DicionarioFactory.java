package Dicionario;

public interface DicionarioFactory {
    Categoria criarCategoria(String nome, String descricao);
    Palavra criarPalavra(String termo, Categoria categoria, String significado, String traducao, String[] exemplos, PronunciacaoStrategy pronuncia);
}
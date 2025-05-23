package Dicionario;

public class DicionarioFactorySimples implements DicionarioFactory {

    @Override
    public Categoria criarCategoria(String nome, String descricao) {
        return new Categoria(nome, descricao);
    }

    @Override
    public Palavra criarPalavra(String termo, Categoria categoria, String significado, String traducao, String[] exemplos, PronunciacaoStrategy pronuncia) {
        return new Palavra(termo, categoria, significado, traducao, exemplos, pronuncia);
    }
}
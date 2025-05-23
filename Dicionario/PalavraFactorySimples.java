package Dicionario;

public class PalavraFactorySimples implements PalavraFactory {
    @Override
    public Palavra criarPalavra(String termo, Categoria categoria, String significado, String etimologia, String[] sinonimos, PronunciacaoStrategy pronunciacao) {
        return new Palavra(termo, categoria, significado, etimologia, sinonimos, pronunciacao);
    }
}


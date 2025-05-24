package Dicionario;

public class PalavraFactorySimples implements PalavraFactory {

    public PalavraFactorySimples() {
    }

    @Override
    public Palavra criarPalavra(String termo, Categoria categoria, String significado, String traducao, String[] exemplos, PronunciacaoStrategy pronunciacao) {
        PronunciacaoStrategy estrategiaPronuncia = pronunciacao;
        if (estrategiaPronuncia == null) {
        estrategiaPronuncia = new PronunciaTextoSimples();
    }

        return new Palavra(termo, categoria, significado, traducao, exemplos, estrategiaPronuncia);
    }
}


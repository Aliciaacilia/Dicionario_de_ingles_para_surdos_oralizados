package Dicionario;

public class DicionarioFactoryPadrao implements DicionarioFactory {

    @Override
    public Categoria criarCategoria(String nome, String descricao) {
        return new Categoria(nome, descricao);
    }

    @Override
    public Palavra criarPalavra(String termo, Categoria categoria, String significado, String traducao, String[] exemplos) {
        PronunciaStrategy estrategiaPronuncia = new PronunciaTextoSimples();
        
        return new Palavra(termo, categoria, significado, traducao, exemplos, estrategiaPronuncia);
    }
}

package Dicionario;

public class DicionarioFactorySimples implements DicionarioFactory {

    @Override
    public Categoria criarCategoria(String nome, String descricao) {
        return new Categoria(nome, descricao);
    }
    
    @Override
    public Palavra criarPalavra(String termo, Categoria categoria, String significado, String traducao, String[] exemplos, PronunciacaoStrategy pronunciacao) {
        PronunciacaoStrategy estrategiaPronuncia;
        
        if (pronunciacao == null) {
            estrategiaPronuncia = new PronunciaTextoSimples();  
        } else {
            estrategiaPronuncia = pronunciacao;  
        }
        
        return new Palavra(termo, categoria, significado, traducao, exemplos, estrategiaPronuncia);
    }

}
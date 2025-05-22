package Dicionario;

public class PalavraFactorySimples implements PalavraFactory {

    private PronunciacaoStrategy pronunciacao;

    public PalavraFactorySimples(PronunciacaoStrategy pronunciacao) {
        this.pronunciacao = pronunciacao;
    }

    @Override
    public Palavra criarPalavra(String termo, Categoria categoria, String significado, String traducao, String[] exemplos) {
        if (termo == null || termo.trim().equals("")) {
            throw new IllegalArgumentException("Informe o termo da palavra.");
        }
        if (categoria == null) {
            throw new IllegalArgumentException("Informe a categoria da palavra.");
        }
        if (significado == null) {
            significado = "";
        }
        if (traducao == null) {
            traducao = "";
        }
        if (exemplos == null) {
            exemplos = new String[0];
        }
        return new Palavra(termo, categoria, significado, traducao, exemplos, pronunciacao);
    }
}


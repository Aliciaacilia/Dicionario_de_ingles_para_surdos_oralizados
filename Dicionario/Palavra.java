package Dicionario;

public class Palavra {
    private String termo;
    private Categoria categoria;
    private String significado;
    private String traducao;
    private String[] exemplos;
    private PronunciaStrategy pronunciacao;

    public Palavra(String termo, Categoria categoria, String significado, String traducao, String[] exemplos, PronunciaStrategy pronunciacao) {
        this.termo = termo;
        this.categoria = categoria;
        this.significado = significado;
        this.traducao = traducao;
        this.exemplos = exemplos;
        this.pronunciacao = pronunciacao;
    }

    public String getTermo() {
        return termo;
    }

    public void setPronunciacao(PronunciaStrategy pronunciacao) {
        this.pronunciacao = pronunciacao;
    }

    public void obterPronuncia() {
        if (pronunciacao != null) {
            pronunciacao.pronunciar(termo);
        } else {
            System.out.println("Nenhuma pronúncia definida.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Termo: ").append(termo)
          .append("\nCategoria: ").append(categoria.getNome())
          .append("\nSignificado: ").append(significado)
          .append("\nTradução: ").append(traducao)
          .append("\nExemplos:\n");
        for (String ex : exemplos) {
            sb.append("- ").append(ex).append("\n");
        }
        return sb.toString();
    }
}

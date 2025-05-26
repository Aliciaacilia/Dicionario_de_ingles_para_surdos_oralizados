package Dicionario;

public class Palavra {
    private Integer id;  
    private String termo;
    private Categoria categoria;
    private String significado;
    private String traducao;
    private String[] exemplos;
    private PronunciaStrategy pronunciacao;

    public Palavra(int id, String termo, Categoria categoria, String significado, String traducao, String[] exemplos, PronunciaStrategy pronunciacao) {
    this.id = id;
    this.termo = termo;
    this.categoria = categoria;
    this.significado = significado;
    this.traducao = traducao;
    this.exemplos = exemplos;
    this.pronunciacao = pronunciacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTermo() {
        return termo;
    }

    public void setTermo(String termo) {
        this.termo = termo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    public String getTraducao() {
        return traducao;
    }

    public void setTraducao(String traducao) {
        this.traducao = traducao;
    }

    public String[] getExemplos() {
        return exemplos;
    }

    public void setExemplos(String[] exemplos) {
        this.exemplos = exemplos;
    }

    public PronunciaStrategy getPronunciacao() {
        return pronunciacao;
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
        sb.append("Termo: ").append(termo).append("\n");
        sb.append("Categoria: ").append(categoria.getNome()).append("\n");
        sb.append("Significado: ").append(significado).append("\n");
        sb.append("Tradução: ").append(traducao).append("\n");
        sb.append("Exemplos:\n");
        if (exemplos != null) {
            for (String ex : exemplos) {
                sb.append("- ").append(ex).append("\n");
            }
        }
        return sb.toString();
    }
}

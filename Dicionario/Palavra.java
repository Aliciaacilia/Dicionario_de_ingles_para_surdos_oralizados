package Dicionario;

public class Palavra {
    private String termo;
    private Categoria categoria;
    private String significado;
    private String traducao;
    private String[] exemplos;
    private PronunciacaoStrategy pronunciacao;

    public Palavra(String termo, Categoria categoria, String significado, String traducao,
                   String[] exemplos, PronunciacaoStrategy pronunciacao) {

        if (termo == null || termo.isBlank()) throw new IllegalArgumentException("Termo inválido.");
        if (categoria == null) throw new IllegalArgumentException("Categoria inválida.");
        if (significado == null || significado.isBlank()) throw new IllegalArgumentException("Significado inválido.");
        if (traducao == null || traducao.isBlank()) throw new IllegalArgumentException("Tradução inválida.");
        if (pronunciacao == null) throw new IllegalArgumentException("Pronúncia inválida.");

        this.termo = termo;
        this.categoria = categoria;
        this.significado = significado;
        this.traducao = traducao;
        this.exemplos = (exemplos != null) ? exemplos.clone() : new String[0];
        this.pronunciacao = pronunciacao;
    }

    public String getTermo() {
        return termo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getSignificado() {
        return significado;
    }

    public String getTraducao() {
        return traducao;
    }

    public String[] getExemplos() {
        return exemplos.clone();
    }

    public PronunciacaoStrategy getPronunciacao() {
        return pronunciacao;
    }

    public void setPronunciacao(PronunciacaoStrategy pronunciacao) {
        if (pronunciacao == null) throw new IllegalArgumentException("Pronúncia inválida.");
        this.pronunciacao = pronunciacao;
    }

    public void obterPronuncia() {
        pronunciacao.pronunciar(termo);
    }

    @Override
    public String toString() {
        return String.format(
                "Termo: %s | Categoria: %s | Significado: %s | Tradução: %s",
                termo, categoria.getNome(), significado, traducao
        );
    }
}

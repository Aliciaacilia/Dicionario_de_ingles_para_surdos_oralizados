package Dicionario;

public class Palavra {
    private String termo;
    private Categoria categoria;
    private String significado;
    private String traducao;
    private String[] exemplos;
    private PronunciacaoStrategy pronunciacao;

    public Palavra(String termo, Categoria categoria, String significado, String traducao, String[] exemplos, PronunciacaoStrategy pronunciacao) {
        validarTermo(termo);
        validarCategoria(categoria);
        validarSignificado(significado);
        validarTraducao(traducao);
        validarPronunciacao(pronunciacao);

        this.termo = termo;
        this.categoria = categoria;
        this.significado = significado;
        this.traducao = traducao;
        setExemplos(exemplos); 
        this.pronunciacao = pronunciacao;
    }

    private void validarTermo(String termo) {
        if (termo == null || termo.trim().equals("")) {
            throw new IllegalArgumentException("Informe o termo da palavra.");
        }
    }

    private void validarCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("Selecione uma categoria para a palavra.");
        }
    }

    private void validarSignificado(String significado) {
        if (significado == null || significado.trim().equals("")) {
            throw new IllegalArgumentException("Informe o significado da palavra.");
        }
    }

    private void validarTraducao(String traducao) {
        if (traducao == null || traducao.trim().equals("")) {
            throw new IllegalArgumentException("Informe a tradução da palavra.");
        }
    }

    private void validarPronunciacao(PronunciacaoStrategy pronunciacao) {
        if (pronunciacao == null) {
            throw new IllegalArgumentException("Defina a pronúncia da palavra.");
        }
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
        String[] copia = new String[exemplos.length];
        for (int i = 0; i < exemplos.length; i++) {
            copia[i] = exemplos[i];
        }
        return copia;
    }

    public PronunciacaoStrategy getPronunciacao() {
        return pronunciacao;
    }

    public void setTermo(String termo) {
        validarTermo(termo);
        this.termo = termo;
    }

    public void setCategoria(Categoria categoria) {
        validarCategoria(categoria);
        this.categoria = categoria;
    }

    public void setSignificado(String significado) {
        validarSignificado(significado);
        this.significado = significado;
    }

    public void setTraducao(String traducao) {
        validarTraducao(traducao);
        this.traducao = traducao;
    }

    public void setExemplos(String[] exemplos) {
        if (exemplos != null) {
            this.exemplos = new String[exemplos.length];
            for (int i = 0; i < exemplos.length; i++) {
                this.exemplos[i] = exemplos[i];
            }
        } else {
            this.exemplos = new String[0];
        }
    }

    public void setPronunciacao(PronunciacaoStrategy pronunciacao) {
        validarPronunciacao(pronunciacao);
        this.pronunciacao = pronunciacao;
    }

    @Override
    public String toString() {
        String resultado = "Termo: " + termo + " ";
        resultado += "Categoria: " + categoria.getNome() + " ";
        resultado += "Significado: " + significado + " ";
        resultado += "Tradução: " + traducao;
        return resultado;
    }
}



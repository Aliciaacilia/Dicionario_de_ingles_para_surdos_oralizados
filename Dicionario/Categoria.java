package Dicionario;

public class Categoria {
    private String nome;
    private String descricao;

    public Categoria(String nome, String descricao) {
        validarNome(nome);
        this.nome = nome;
        this.descricao = descricao;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.trim().equals("")) {
            throw new IllegalArgumentException("Informe o nome da categoria.");
        }
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setNome(String nome) {
        validarNome(nome);
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (objeto == null) {
            return false;
        }
        if (!objeto.getClass().equals(this.getClass())) {
            return false;
        }
        Categoria outraCategoria = (Categoria) objeto;
        return this.nome.equals(outraCategoria.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    @Override
    public String toString() {
        return "Categoria: " + nome + " | Descrição: " + descricao;
    }
}



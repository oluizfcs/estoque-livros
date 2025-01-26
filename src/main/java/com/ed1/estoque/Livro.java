package com.ed1.estoque;

/**
 *
 * @author luiz
 */
public class Livro {
    String titulo;
    String autor;
    Integer anoDePublicacao;
    Integer quantidadeNoEstoque;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public void setAnoDePublicacao(Integer anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    public Integer getQuantidadeNoEstoque() {
        return quantidadeNoEstoque;
    }

    public void setQuantidadeNoEstoque(Integer quantidadeNoEstoque) {
        this.quantidadeNoEstoque = quantidadeNoEstoque;
    }
}

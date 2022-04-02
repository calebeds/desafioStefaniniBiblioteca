package br.com.stefanini.developerup.dto;

import br.com.stefanini.developerup.model.Autor;

/**
 * @author Calebe Oliveira
 *         email calebe.dso@gmail.com
 *         created 30/03/2022
 * @version 0.1.0
 */
public class LivroDto {

    private long isbn10;
    private long isbn13;
    private String nome;
    private Autor autor;
    private String editora;
    private int anoDePublicacao;
    private int qtdDeExemplares;

    // Setters and getters

    public long getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(long isbn10) {
        this.isbn10 = isbn10;
    }

    public long getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(long isbn13) {
        this.isbn13 = isbn13;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
    public int getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public void setAnoDePublicacao(int anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    public String getEditora() {
        return this.editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getQtdDeExemplares() {
        return qtdDeExemplares;
    }

    public void setQtdDeExemplares(int qtdDeExemplares) {
        this.qtdDeExemplares = qtdDeExemplares;
    }

}

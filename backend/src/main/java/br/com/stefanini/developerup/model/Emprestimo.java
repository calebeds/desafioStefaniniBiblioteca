package br.com.stefanini.developerup.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Calebe Oliveira
 * email calebe.dso@gmail.com
 * created 30/03/2022
 * @version 0.1.0
 */
@Entity
@Table(name = "emprestimo")
public class Emprestimo extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //Coloquei como chave primária for no specific reason

    @Column(name = "emailCliente")
    private String emailCliente;

    
    @Column(name = "isbn")
    private long isbn;

    @Column(name = "dataDeInicio")
    private String dataDeInicio;

    @Column(name = "dataDeEntrega")
    private String dataDeEntrega;

    

    //Setters and getters

    //if qtd de emprestimos > 3, throw exception
    //if emprestimos is done, then just

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getDataDeInicio() {
        return dataDeInicio;
    }

    public void setDataDeInicio(String dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
    }

    public String getDataDeEntrega() {
        return dataDeEntrega;
    }

    public void setDataDeEntrega(String dataDeEntrega) {
        this.dataDeEntrega = dataDeEntrega;
    }

    
    
}

package br.com.stefanini.developerup.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Calebe Oliveira
 * email calebe.dso@gmail.com
 * created 30/03/2022
 * @version 0.1.0
 */
@Entity
@Table(name = "autor")
public class Autor extends PanacheEntityBase {

    @Id
    private long isni;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "dataDeNascimento")
    private String dataDeNascimento;

    @Column(name = "biografia")
    private String biografia;
    

    //Setters and getters

    public long getIsni() {
        return isni;
    }

    public void setIsni(long isni) {
        this.isni = isni;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
    
}

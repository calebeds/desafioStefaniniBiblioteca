package br.com.stefanini.developerup.dao;

import br.com.stefanini.developerup.dto.LivroDto;
import br.com.stefanini.developerup.model.Autor;
import br.com.stefanini.developerup.model.Livro;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import java.util.List;

/**
 * @author Danilo Dorgam
 * email danilodorgam@gmail.com
 * created 30/03/2022
 * @version 0.1.0
 */
@RequestScoped
public class LivroDao {
    public List<Livro> listar(){
        return Livro.listAll(Sort.by("isbn10, isbn13, nome, autor, anoDePublicacao, editora, qtdDeExemplares").ascending());
    }

    @Transactional
    public void inserir(Livro livro) { 
        List<Autor> autorDoLivro = Autor.list("isni", livro.getAutor().getIsni());//Obtendo o autor do livro
        livro.setAutor(autorDoLivro.stream().findAny().get());//Setando o autor do livro
;
        livro.persist();// Persistindo no bd

    }

    @Transactional
    public void editar(LivroDto livro, long isbnGen) {

        List<Autor> autorDoLivro = Autor.list("isni", livro.getAutor().getIsni());//Obtendo o autor do livro
        livro.setAutor(autorDoLivro.stream().findAny().get());//Setando o autor do livro
        
        int updatedLivros = Livro.update(
            "isbn10 = ?1, isbn13 = ?2, nome = ?3, autor = ?4, anoDePublicacao = ?5, editora = ?6, qtdDeExemplares = ?7 WHERE isbn10 = ?8 OR isbn13 = ?9", 
           livro.getIsbn10(), livro.getIsbn13(), livro.getNome(), livro.getAutor(), livro.getAnoDePublicacao(), livro.getEditora(), livro.getQtdDeExemplares(), isbnGen, isbnGen
        );

        if(updatedLivros <= 0) { //Se não editar nenhum
            throw new NotFoundException();
        }
    }

    @Transactional
    public void deletar(long isbn) {
        System.out.println(isbn);
        long deletedIsbn10 = Livro.delete("isbn10", isbn);
        long deletedIsbn13 = Livro.delete("isbn13", isbn);

        if(deletedIsbn13  <= 0 && deletedIsbn10 <= 0) { // Se não deletar nenhum
                throw new NotFoundException();
        }
    }

}

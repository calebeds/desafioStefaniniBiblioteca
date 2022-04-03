package br.com.stefanini.developerup.dao;

import br.com.stefanini.developerup.dto.AutorDto;
import br.com.stefanini.developerup.model.Autor;
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
public class AutorDao {
    public List<Autor> listar(){
        return Autor.listAll(Sort.by("isni, nome, email, dataDeNascimento, biografia").ascending());
    }

    public List<Autor> listarUmAutor(long isni){ 
      return Autor.list("isni", isni);
    }

    @Transactional
    public void inserir(Autor autor) { 
        autor.persist();// Persistindo no bd
    }

    @Transactional
    public void editar(AutorDto autor, long isni) {
        int updatedAuthors = Autor.update("isni = ?1, nome = ?2, email = ?3, dataDeNascimento = ?4, biografia = ?5 where isni =?6", 
            autor.getIsni(), autor.getNome(), autor.getEmail(), autor.getDataDeNascimento(), autor.getBiografia(), isni);
        
        if(updatedAuthors <= 0) { //Se não houver nenhum a ser editado
             throw new NotFoundException();
         }
      
    }

    @Transactional
    public void deletar(long isni) {
        long deletedIsinis = Autor.delete("isni", isni);

        if(deletedIsinis <= 0) { // Se não deletar nenhum
                throw new NotFoundException();
        }
    }

}

package br.com.stefanini.developerup.service;



import br.com.stefanini.developerup.dao.AutorDao;
import br.com.stefanini.developerup.dto.AutorDto;
import br.com.stefanini.developerup.parser.AutorParser;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Danilo Dorgam
 * email danilodorgam@gmail.com
 * created 30/03/2022
 * @version 0.1.0
 */
@RequestScoped
public class AutorService {
    @Inject
    AutorDao dao;

    public List<AutorDto> listar(){
        return dao.listar().stream().map(AutorParser.get()::dto).collect(Collectors.toList());
    }

    public AutorDto listarUmAutor(String isni) {
         return dao.listarUmAutor(Long.parseLong(isni)).stream().map(AutorParser.get()::dto).findFirst().get();
    }


    public void inserir(AutorDto autor) {
            dao.inserir(
                AutorParser.get().dtoToAutor(autor)
            );
    }

    public void editar(AutorDto autor, String isni) {
        dao.editar(autor, Long.parseLong(isni));
    }

    public void deletar(String isni) {
        dao.deletar(Long.parseLong(isni));
    }
}

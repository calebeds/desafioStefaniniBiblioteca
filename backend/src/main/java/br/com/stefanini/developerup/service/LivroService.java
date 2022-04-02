package br.com.stefanini.developerup.service;



import br.com.stefanini.developerup.dao.LivroDao;
import br.com.stefanini.developerup.dto.LivroDto;
import br.com.stefanini.developerup.parser.LivroParser;

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
public class LivroService {
    @Inject
    LivroDao dao;

    public List<LivroDto> listar(){
        return dao.listar().stream().map(LivroParser.get()::dto).collect(Collectors.toList()); //Transformando optional em List
    }

    public LivroDto listarUmLivro(String busca) {


        return dao.listar()
            .stream()
            .map(LivroParser.get()::dto)
            .filter(
                livro -> {
                    return Boolean.TRUE.equals(
                        livro.getNome().contains(busca) // Se contiver uma parte da string
                        || Long.parseLong(busca) == livro.getIsbn10() // Se for o isbn10
                        || Long.parseLong(busca) == livro.getIsbn13() // Se for o isbn13
                    ); //Prevent null pointer or try to
            })
            .findFirst() //An optional
            .get();
    }


    public void inserir(LivroDto livroDto) {
            dao.inserir(
                LivroParser.get().dtoToLivro(livroDto)
            );
    }

    public void editar(LivroDto livroDto, String isbn) {
        dao.editar(livroDto, Long.parseLong(isbn));
    }

    public void deletar(String isbn) {
        dao.deletar(Long.parseLong(isbn));
    }
}

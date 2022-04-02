package br.com.stefanini.developerup.parser;

import br.com.stefanini.developerup.dto.LivroDto;
import br.com.stefanini.developerup.model.Livro;

/**
 * @author Danilo Dorgam
 * email danilodorgam@gmail.com
 * created 30/03/2022
 * @version 0.1.0
 */
public class LivroParser {
    public static LivroParser get(){
        return  new LivroParser();
    }

    public LivroDto dto(Livro entidade){
        LivroDto dto = new LivroDto();

        dto.setIsbn10(entidade.getIsbn10());
        dto.setIsbn13(entidade.getIsbn13());
        dto.setNome(entidade.getNome());
        dto.setEditora(entidade.getEditora());
        dto.setAutor(entidade.getAutor());
        dto.setAnoDePublicacao(entidade.getAnoDePublicacao());
        dto.setQtdDeExemplares(entidade.getQtdDeExemplares());
  
        return dto;
    }

    public Livro dtoToLivro(LivroDto entidade) { //Método para fazer o contrário, pois se recebermos um dto, teremos de inserir no bd um Livro por exemplo
        Livro livro = new Livro();

        livro.setIsbn10(entidade.getIsbn10());
        livro.setIsbn13(entidade.getIsbn13());
        livro.setNome(entidade.getNome());
        livro.setEditora(entidade.getEditora());
        livro.setAutor(entidade.getAutor());
        livro.setAnoDePublicacao(entidade.getAnoDePublicacao());
        livro.setQtdDeExemplares(entidade.getQtdDeExemplares());

        return livro;
    }
}

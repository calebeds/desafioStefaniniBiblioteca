package br.com.stefanini.developerup.parser;

import br.com.stefanini.developerup.dto.AutorDto;
import br.com.stefanini.developerup.model.Autor;

/**
 * @author Danilo Dorgam
 * email danilodorgam@gmail.com
 * created 30/03/2022
 * @version 0.1.0
 */
public class AutorParser {
    public static AutorParser get(){
        return  new AutorParser();
    }

    public AutorDto dto(Autor entidade){
        AutorDto dto = new AutorDto();

        dto.setIsni(entidade.getIsni());
        dto.setNome(entidade.getNome());
        dto.setEmail(entidade.getEmail());
        dto.setDataDeNascimento(entidade.getDataDeNascimento());
        dto.setBiografia(entidade.getBiografia());
  
        return dto;
    }

    public Autor dtoToAutor(AutorDto entidade) { //Método para fazer o contrário, pois se recebermos um dto, teremos de inserir no bd um autor por exemplo
        Autor autor = new Autor();

        autor.setIsni(entidade.getIsni());
        autor.setEmail(entidade.getEmail());
        autor.setNome(entidade.getNome());
        autor.setDataDeNascimento(entidade.getDataDeNascimento());
        autor.setBiografia(entidade.getBiografia());
        return autor;
    }
}

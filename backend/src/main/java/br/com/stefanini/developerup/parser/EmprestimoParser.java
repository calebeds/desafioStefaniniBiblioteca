package br.com.stefanini.developerup.parser;

import br.com.stefanini.developerup.dto.EmprestimoDto;
import br.com.stefanini.developerup.model.Emprestimo;

/**
 * @author Danilo Dorgam
 * email danilodorgam@gmail.com
 * created 30/03/2022
 * @version 0.1.0
 */
public class EmprestimoParser {
    public static EmprestimoParser get(){
        return  new EmprestimoParser();
    }

    public EmprestimoDto dto(Emprestimo entidade){
        EmprestimoDto dto = new EmprestimoDto();

        dto.setId(entidade.getId());
        dto.setIsbn(entidade.getIsbn());
        dto.setEmailCliente(entidade.getEmailCliente());
        dto.setDataDeInicio(entidade.getDataDeInicio());
        
        return dto;
    }

    public Emprestimo dtoToEmprestimo(EmprestimoDto entidade) { //Método para fazer o contrário, pois se recebermos um dto, teremos de inserir no bd um Emprestimo por exemplo
        Emprestimo emprestimo = new Emprestimo();

        emprestimo.setId(entidade.getId());
        emprestimo.setIsbn(entidade.getIsbn());
        emprestimo.setEmailCliente(entidade.getEmailCliente());
        emprestimo.setDataDeInicio(entidade.getDataDeInicio());
        return emprestimo;
    }
}

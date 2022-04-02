package br.com.stefanini.developerup.parser;

import br.com.stefanini.developerup.dto.ClienteDto;
import br.com.stefanini.developerup.model.Cliente;

/**
 * @author Danilo Dorgam
 * email danilodorgam@gmail.com
 * created 30/03/2022
 * @version 0.1.0
 */
public class ClienteParser {
    public static ClienteParser get(){
        return  new ClienteParser();
    }

    public ClienteDto dto(Cliente entidade){
        ClienteDto dto = new ClienteDto();

        dto.setEmail(entidade.getEmail());
        dto.setNome(entidade.getNome());
        dto.setContato(entidade.getContato());
        dto.setQtdEmprestimos(entidade.getQtdEmprestimos());
        return dto;
    }

    public Cliente dtoToCliente(ClienteDto entidade) { //Método para fazer o contrário, pois se recebermos um dto, teremos de inserir no bd um cliente por exemplo
        Cliente cliente = new Cliente();

        cliente.setEmail(entidade.getEmail());
        cliente.setNome(entidade.getNome());
        cliente.setContato(entidade.getContato());
        cliente.setQtdEmprestimos(entidade.getQtdEmprestimos());
        return cliente;
    }
}

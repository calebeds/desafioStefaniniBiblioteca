package br.com.stefanini.developerup.service;



import br.com.stefanini.developerup.dao.ClienteDao;
import br.com.stefanini.developerup.dto.ClienteDto;
import br.com.stefanini.developerup.parser.ClienteParser;

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
public class ClienteService {
    @Inject
    ClienteDao dao;

    public List<ClienteDto> listar(){
        return dao.listar().stream().map(ClienteParser.get()::dto).collect(Collectors.toList());
    }

    public ClienteDto listarUmCliente(String busca) {
        return dao.listar()
            .stream()
            .map(ClienteParser.get()::dto)
            .filter(pessoa -> {
                System.out.println(pessoa);
                return Boolean.TRUE.equals(pessoa.getNome().contains(busca) || pessoa.getEmail().equalsIgnoreCase(busca)); //Prevent null pointer or try to
            })
            .findFirst() //An optional
            .get();
         
    }


    public void inserir(ClienteDto cliente) {
            cliente.setQtdEmprestimos(0);
            dao.inserir(
                ClienteParser.get().dtoToCliente(cliente)//Transformando o dto em cliente para ser inserido no bd
            );
    }

    public void editar(ClienteDto cliente, String busca) {
        dao.editar(cliente, busca);
    }

    public void deletar(String busca) {
        dao.deletar(busca);
    }
}

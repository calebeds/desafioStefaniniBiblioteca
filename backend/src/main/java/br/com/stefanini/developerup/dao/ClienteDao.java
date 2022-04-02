package br.com.stefanini.developerup.dao;

import br.com.stefanini.developerup.dto.ClienteDto;
import br.com.stefanini.developerup.model.Cliente;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import java.util.List;

/**
 * @author Calebe Oliveira
 * email calebe.dso@gmail.com
 * created 30/03/2022
 * @version 0.1.0
 */
@RequestScoped
public class ClienteDao {
    public List<Cliente> listar(){
        return Cliente.listAll(Sort.by("nome,email,contato, qtdEmprestimos").ascending());
    }

    /*public List<Cliente> listarUmCliente(String busca){ //Não é necessário
      return Cliente.list("nome", busca);
    }*/

    @Transactional
    public void inserir(Cliente cliente) { //Talvez necessite outro parser??
        cliente.persist();// Persistindo no bd
    }

    @Transactional
    public void editar(ClienteDto cliente, String busca) {

        int updated = 0;
        
        if(Boolean.TRUE.equals(cliente.getQtdEmprestimos() == 0) ) { //Se não houver empréstimos, apenas edite os outros campos
            
            updated = Cliente.update("nome = ?1, contato = ?2, email = ?3 WHERE nome = ?4 OR email = ?5", 
                cliente.getNome(), cliente.getContato(), cliente.getEmail(), busca, busca);
        } else { // caso haja, adicione
            
            updated = Cliente.update("nome = ?1, contato = ?2, email = ?3, qtdEmprestimos = ?4 WHERE nome = ?5 OR email = ?6", 
                cliente.getNome(), cliente.getContato(), cliente.getEmail(), cliente.getQtdEmprestimos(), busca, busca);
        }
        

        if(updated <= 0) { //Se não editar nenhum
            throw new NotFoundException();
        }
    }

    @Transactional
    public void deletar(String busca) {
        System.out.println(busca);
        long deletedNome = Cliente.delete("nome", busca);
        long deletedEmail = Cliente.delete("email", busca);

        if(deletedNome <= 0 && deletedEmail <= 0) { // Se não deletar nenhum
                throw new NotFoundException();
        }
    }

}

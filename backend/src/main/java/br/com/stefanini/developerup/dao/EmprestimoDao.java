package br.com.stefanini.developerup.dao;

import br.com.stefanini.developerup.dto.ClienteDto;
import br.com.stefanini.developerup.dto.EmprestimoDto;
import br.com.stefanini.developerup.model.Cliente;
import br.com.stefanini.developerup.model.Emprestimo;
import br.com.stefanini.developerup.model.Livro;
import br.com.stefanini.developerup.parser.ClienteParser;
import br.com.stefanini.developerup.parser.EmprestimoParser;
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
public class EmprestimoDao {
    public List<Emprestimo> listar(){ //ALL
        return Emprestimo.listAll(Sort.by("id").ascending());
    }

    public List<Emprestimo> listarUmEmprestimo(long id){ //Only one 
      return Emprestimo.list("id", id);
    }

    @Transactional
    public void inserir(Emprestimo emprestimo) {  


        if(emprestimo.isPersistent()) {
            throw new IllegalArgumentException("Empréstimo de um exemplar apenas!");
        }

        //Parte do empréstimo
        ////
        Cliente clienteEmprestimo =
            (Cliente) Cliente.list("email", emprestimo.getEmailCliente()).get(0); //Obtendo o cliente que se tem do empréstimo a fazer
        
        if(Boolean.TRUE.equals(clienteEmprestimo == null)) {
            throw new NotFoundException("Esse cliente não existe!");
        }
        

        int qtdDeEmprestimos = clienteEmprestimo.getQtdEmprestimos();//Quantidade de empréstimos de um cliente

        if(qtdDeEmprestimos < 0) { 
            throw new NotFoundException("Erro, quantidade de empréstimos não pode ser menor que 0!!");
        } else if(qtdDeEmprestimos < 3 ){ // Se o cliente possuir até 2 empréstimos, adicione mais um 
            Cliente.update("qtdEmprestimos = ?1 WHERE email = ?2", ++qtdDeEmprestimos, emprestimo.getEmailCliente());//Aumentando a quantidade de empréstimos em Clientes
        
        } else {
            throw new IndexOutOfBoundsException("Número máximo de empréstimos excedido");
        }

        ///Parte dos exemplar/Livro

        Livro livro = (Livro) Livro.list("isbn10", emprestimo.getIsbn()).get(0); //O livro do empréstimo

        if(Boolean.TRUE.equals(livro == null)) {
            throw new NotFoundException("Esse livro não existe!");
        }

        int qtdExemplares = livro.getQtdDeExemplares();


        if(qtdExemplares < 0) { 
            throw new NotFoundException("Erro, quantidade de exemplares não pode ser menor que 0!!");
        } else if(qtdExemplares > 0 ){ // Se o cliente possuir até 2 empréstimos, adicione mais um 
            Livro.update("qtdDeExemplares = ?1 WHERE isbn10 = ?2", --qtdExemplares, livro.getIsbn10());//Aumentando a quantidade de empréstimos em Clientes
        
        } else if(qtdExemplares == 0) {
            throw new IndexOutOfBoundsException("Não há mais exemplares a ser emprestados");
        }

       //Finalmente escrevendo o empréstimo no BD

        emprestimo.persist();// Persistindo emprestimo no bd


        
    }

    @Transactional
    public void editar(EmprestimoDto emprestimo, long id) {

        if(emprestimo.getId() != id ) {
            throw new IllegalArgumentException("Id do empréstimo tem de ser o mesmo do parâmetro");
        }

        int updatedEmprestimos = Emprestimo.update("emailCliente = ?1, isbn = ?2, dataDeInicio = ?3, dataDeEntrega = ?4 where id = ?5", 
            emprestimo.getEmailCliente(), emprestimo.getIsbn(), emprestimo.getDataDeInicio(), emprestimo.getDataDeEntrega(), emprestimo.getId());
        
        if(updatedEmprestimos <= 0) { //Se não houver nenhum a ser editado
             throw new NotFoundException();
         }
      
    }

    @Transactional
    public void deletar(long id) {

        //Subtraindo da devolução de um livro
        EmprestimoDto ep = EmprestimoParser.get().dto((Emprestimo) Emprestimo.list("id", id).get(0));

        if(Boolean.TRUE.equals(ep == null))
            throw new NotFoundException("Esse empréstimo não existe!! Id não encontrado!");


        ClienteDto cd = ClienteParser.get().dto((Cliente) Cliente.list("email", ep.getEmailCliente()).get(0));

        if(Boolean.TRUE.equals(cd == null)) 
            throw new NotFoundException("Cliente com esse empréstimo não foi encontrado!! Verifique!");


        int qtdEmprestimos = cd.getQtdEmprestimos();


        Cliente.update("qtdEmprestimos = ?1 WHERE email = ?2", --qtdEmprestimos, ep.getEmailCliente()); //Diminuindo a quantidade de empréstimos


        Livro livro = (Livro) Livro.list("isbn10", ep.getIsbn()).get(0);

        if(Boolean.TRUE.equals(livro == null)) {
            throw new NotFoundException("Esse livro não existe!");
        }

        int qtdExemplares = livro.getQtdDeExemplares();


        if(qtdExemplares < 0) { 
            throw new IllegalArgumentException("A quantidade de exemplares não deve ser menor que 0. Algo está errado!!");
        } else {  
            Livro.update("qtdDeExemplares = ?1 WHERE isbn10 = ?2", ++qtdExemplares, livro.getIsbn10());//Aumentando a quantidade de empréstimos em Clientes
        } 


        long deletedIsinis = Emprestimo.delete("id", id);


        if(deletedIsinis <= 0) { // Se não deletar nenhum
                throw new NotFoundException();
        }
    }

}

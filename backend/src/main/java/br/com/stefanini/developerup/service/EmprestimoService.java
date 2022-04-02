package br.com.stefanini.developerup.service;



import br.com.stefanini.developerup.dao.EmprestimoDao;
import br.com.stefanini.developerup.dto.EmprestimoDto;
import br.com.stefanini.developerup.parser.EmprestimoParser;

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
public class EmprestimoService {
    @Inject
    EmprestimoDao dao;

    public List<EmprestimoDto> listar(){
        return dao.listar().stream().map(EmprestimoParser.get()::dto).collect(Collectors.toList()); //Transformando optional em List
    }

    public EmprestimoDto listarUmEmprestimo(long id) {
        return dao.listarUmEmprestimo(id).stream().map(EmprestimoParser.get()::dto).findFirst().get();
    }


    public void inserir(EmprestimoDto emprestimo) {
            dao.inserir(
                EmprestimoParser.get().dtoToEmprestimo(emprestimo)
            );
    }

    public void editar(EmprestimoDto emprestimoDto, long id) {
        dao.editar(emprestimoDto, id);
    }

    public void deletar(long id) {
        dao.deletar(id);
    }
}

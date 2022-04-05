package br.com.stefanini.developerup.rest;

import br.com.stefanini.developerup.dto.EmprestimoDto;
import br.com.stefanini.developerup.service.EmprestimoService;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * @author Danilo Dorgam
 * email danilodorgam@gmail.com
 * created 30/03/2022
 * @version 0.1.0
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/emprestimo")
@RequestScoped

public class EmprestimoRest {
    @Inject
    EmprestimoService service;

    //GETS

    @GET
    @Operation(summary = "Listar", description = "Retorna uma lista de Emprestimos")
    @APIResponse(responseCode = "200", description = "EmprestimoDto",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmprestimoDto.class))})
    public Response listar()  {
        return Response.status(Response.Status.OK).entity(service.listar()).build();
    }


    @Path("{id}")
    @GET
    @Operation(summary = "Listar", description = "Retorna um emprestimo de um parâmetro específico")
    @APIResponse(responseCode = "200", description = "EmprestimoDto",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmprestimoDto.class))})
    public Response listarUmEmprestimo(@PathParam("id") long id)  { //Usando generics
        return Response.status(Response.Status.OK).entity(service.listarUmEmprestimo(id)).build();
    }

    //POST

    @POST
    @Operation(summary = "Inserir", description = "Cria um Emprestimo")
    @APIResponse(responseCode = "201", description = "Emprestimo criado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmprestimoDto.class))})
    public Response inserirAutor(EmprestimoDto emprestimo)  {
        service.inserir(emprestimo);//Insere
        return Response.status(Response.Status.CREATED).build();//Status created
    }


    //PUT 

    @Path("{id}")
    @PUT
    @Operation(summary = "Editar", description = "Edita um autor")
    @APIResponse(responseCode = "201", description = "autor editado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmprestimoDto.class))})
    public Response editarAutor(EmprestimoDto emprestimoDto, @PathParam("id") long id)  {
        service.editar(emprestimoDto, id);//Edita pelo id
        return Response.status(Response.Status.CREATED).build();//Status created
    }

    //Delete
    @Path("{id}")
    @DELETE
    @Operation(summary = "Deletar", description = "Deleta um autor")
    @APIResponse(responseCode = "200", description = "autor deletado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmprestimoDto.class))})
    public Response deletarautor(@PathParam("id") long id)  {
        service.deletar(id);//Deleta pelo isbn
        return Response.status(Response.Status.OK).build();//Status Ok
    }

}

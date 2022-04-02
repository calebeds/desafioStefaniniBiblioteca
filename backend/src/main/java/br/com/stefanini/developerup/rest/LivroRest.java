package br.com.stefanini.developerup.rest;

import br.com.stefanini.developerup.dto.LivroDto;
import br.com.stefanini.developerup.service.LivroService;

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
@Path("/livro")
@RequestScoped

public class LivroRest {
    @Inject
    LivroService service;

    //GETS

    @GET
    @Operation(summary = "Listar", description = "Retorna uma lista de Livros")
    @APIResponse(responseCode = "200", description = "LivroDto",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = LivroDto.class))})
    public Response listar()  {
        return Response.status(Response.Status.OK).entity(service.listar()).build();
    }


    @Path("{busca}")
    @GET
    @Operation(summary = "Listar", description = "Retorna um autor de um parâmetro específico")
    @APIResponse(responseCode = "200", description = "LivroDto",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = LivroDto.class))})
    public Response listarUmLivro(@PathParam("busca") String busca)  { //Usando generics
        return Response.status(Response.Status.OK).entity(service.listarUmLivro(busca)).build();
    }

    //POST

    @POST
    @Operation(summary = "Inserir", description = "Cria um livro")
    @APIResponse(responseCode = "201", description = "Livro criado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = LivroDto.class))})
    public Response inserirAutor(LivroDto livro)  {
        service.inserir(livro);//Insere
        return Response.status(Response.Status.CREATED).build();//Status created
    }


    //PUT 

    @Path("{isbn}")
    @PUT
    @Operation(summary = "Editar", description = "Edita um autor")
    @APIResponse(responseCode = "201", description = "autor editado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = LivroDto.class))})
    public Response editarAutor(LivroDto autor, @PathParam("isbn") String isbn)  {
        service.editar(autor, isbn);//Edita pelo isbn
        return Response.status(Response.Status.CREATED).build();//Status created
    }

    //Delete
    @Path("{isbn}")
    @DELETE
    @Operation(summary = "Deletar", description = "Deleta um autor")
    @APIResponse(responseCode = "201", description = "autor deletado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = LivroDto.class))})
    public Response deletarautor(@PathParam("isbn") String isbn)  {
        service.deletar(isbn);//Deleta pelo isbn
        return Response.status(Response.Status.OK).build();//Status Ok
    }

}

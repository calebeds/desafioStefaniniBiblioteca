package br.com.stefanini.developerup.rest;

import br.com.stefanini.developerup.dto.AutorDto;
import br.com.stefanini.developerup.service.AutorService;
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
@Path("/autor")
@RequestScoped

public class AutorRest {
    @Inject
    AutorService service;

    //GETS

    @GET
    @Operation(summary = "Listar", description = "Retorna uma lista de Autores")
    @APIResponse(responseCode = "200", description = "AutorDto",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = AutorDto.class))})
    public Response listar()  {
        return Response.status(Response.Status.OK).entity(service.listar()).build();
    }


    @Path("{isni}")
    @GET
    @Operation(summary = "Listar", description = "Retorna um autor de um parâmetro específico")
    @APIResponse(responseCode = "200", description = "AutorDto",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = AutorDto.class))})
    public Response listarUmAutor(@PathParam("isni") String isni)  {
        return Response.status(Response.Status.OK).entity(service.listarUmAutor(isni)).build();
    }

    //POST

    @POST
    @Operation(summary = "Inserir", description = "Cria um autor")
    @APIResponse(responseCode = "201", description = "Autor criado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = AutorDto.class))})
    public Response inserirAutor(AutorDto autor)  {
        service.inserir(autor);//Insere
        return Response.status(Response.Status.CREATED).build();//Status created
    }


    //PUT 

    @Path("{isni}")
    @PUT
    @Operation(summary = "Editar", description = "Edita um autor")
    @APIResponse(responseCode = "201", description = "autor editado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = AutorDto.class))})
    public Response editarAutor(AutorDto autor, @PathParam("isni") String isni)  {
        service.editar(autor, isni);//Edita pelo nome ou email
        return Response.status(Response.Status.CREATED).build();//Status created
    }

    //Delete
    @Path("{isni}")
    @DELETE
    @Operation(summary = "Deletar", description = "Deleta um autor")
    @APIResponse(responseCode = "200", description = "Autor deletado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = AutorDto.class))})
    public Response deletarautor(@PathParam("isni") String isni)  {
        service.deletar(isni);//Deleta pelo nome ou email
        return Response.status(Response.Status.OK).build();//Status Ok
    }




}

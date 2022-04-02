package br.com.stefanini.developerup.rest;

import br.com.stefanini.developerup.dto.ClienteDto;
import br.com.stefanini.developerup.service.ClienteService;
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
@Path("/cliente")
@RequestScoped

public class ClienteRest {
    @Inject
    ClienteService service;

    //GETS

    @GET
    @Operation(summary = "Listar", description = "Retorna uma lista de Clientes")
    @APIResponse(responseCode = "200", description = "ClienteDto",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDto.class))})
    public Response listar()  {
        return Response.status(Response.Status.OK).entity(service.listar()).build();
    }


    @Path("{busca}")
    @GET
    @Operation(summary = "Listar", description = "Retorna um cliente de um parâmetro específico")
    @APIResponse(responseCode = "200", description = "Cliente",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDto.class))})
    public Response listarUmCliente(@PathParam("busca") String busca)  {
        return Response.status(Response.Status.OK).entity(service.listarUmCliente(busca)).build();
    }

    //POST

    @POST
    @Operation(summary = "Inserir", description = "Cria um cliente")
    @APIResponse(responseCode = "201", description = "Cliente criado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDto.class))})
    public Response inserirCliente(ClienteDto cliente)  {
        service.inserir(cliente);//Insere
        return Response.status(Response.Status.CREATED).build();//Status created
    }


    //PUT 

    @Path("{busca}")
    @PUT
    @Operation(summary = "Editar", description = "Edita um cliente")
    @APIResponse(responseCode = "201", description = "Cliente editado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDto.class))})
    public Response editarCliente(ClienteDto cliente, @PathParam("busca") String busca)  {
        service.editar(cliente, busca);//Edita pelo nome ou email
        return Response.status(Response.Status.CREATED).build();//Status created
    }

    //Delete
    @Path("{busca}")
    @DELETE
    @Operation(summary = "Deletar", description = "Deleta um cliente")
    @APIResponse(responseCode = "201", description = "Cliente deletado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDto.class))})
    public Response deletarCliente(@PathParam("busca") String busca)  {
        service.deletar(busca);//Deleta pelo nome ou email
        return Response.status(Response.Status.OK).build();//Status Ok
    }




}

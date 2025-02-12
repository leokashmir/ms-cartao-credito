package br.com.mvp.cartoes.cliente.presentation.controller;

import br.com.mvp.cartoes.cliente.application.service.ClienteService;
import br.com.mvp.cartoes.cliente.presentation.dto.ClienteRequestDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {


    private ClienteService clienteService;

    @Inject
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @POST
    public Response criarCliente(ClienteRequestDTO userRequestDTO) {
        clienteService.cadastrarCliente(userRequestDTO );
        return Response.status(Response.Status.CREATED).build();
    }

    @PATCH
    public Response updateCliente(ClienteRequestDTO userRequestDTO) {
        clienteService.atualizarCliente(userRequestDTO);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/{document}")
    public Response getClienteByDocument(@PathParam("document") String documento) {

        return Response.ok(clienteService.buscarPorDocumento(documento)).build();
    }

    @DELETE
    @Path("/{document}")
    public Response deleteCliente(@PathParam("document") Long id) {
        clienteService.excluirCliente(id);
        return Response.noContent().build();
    }


}

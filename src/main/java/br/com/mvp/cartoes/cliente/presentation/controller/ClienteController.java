package br.com.mvp.cartoes.cliente.presentation.controller;

import br.com.mvp.cartoes.cliente.application.service.ClienteService;
import br.com.mvp.cartoes.cliente.presentation.dto.ClientePatchRequestDTO;
import br.com.mvp.cartoes.cliente.presentation.dto.ClientePostRequestDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(
                title = "Minha API",
                version = "1.0.0",
                description = "API para gerenciar entidades do sistema."
        ),
        tags = {
                @Tag(name = "Clientes", description = "Operações relacionadas aos clientes"),
                @Tag(name = "Cartões", description = "Operações relacionadas aos cartões")
        }
)
@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {


    private ClienteService clienteService;

    @Inject
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @POST
    public Response criarCliente(ClientePostRequestDTO userRequestDTO) {
        clienteService.cadastrarCliente(userRequestDTO );
        return Response.status(Response.Status.CREATED).build();
    }

    @PATCH
    public Response updateCliente(ClientePatchRequestDTO userRequestDTO) {
        clienteService.atualizarCliente(userRequestDTO);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    public Response getClienteByDocument(@HeaderParam("X-document") String documento) {

        return Response.ok(clienteService.buscarPorDocumento(documento)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCliente(@PathParam("id") Long id) {
        clienteService.excluirCliente(id);
        return Response.ok().build();
    }


}

package br.com.mvp.cartoes.cartao.presentation.controller;

import br.com.mvp.cartoes.cartao.application.service.ContaService;
import br.com.mvp.cartoes.cartao.presentation.dto.ContaPutRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/conta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContaController {

    private ContaService contaService;

    @Inject
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GET
    @Path("/{idCliente}")
    public Response listarContasCliente(@PathParam("idCliente") Long idCliente) {
        return Response.ok(contaService.listarContas(idCliente)).build();
    }

    @DELETE
    @Path("/{idConta}")
    public Response excluirContasCliente(@PathParam("idConta") Long idConta) {
        contaService.excluirContaCliente(idConta);
        return Response.ok().build();
    }

    @PUT
    @Path("/cancelar")
    public Response cancelarConta(ContaPutRequest request) {
        contaService.desativarContaCliente(request.getIdCliente());
        return Response.ok().build();
    }

}

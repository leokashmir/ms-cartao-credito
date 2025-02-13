package br.com.mvp.cartoes.cartao.presentation.controller;


import br.com.mvp.cartoes.cartao.application.service.CartaoService;
import br.com.mvp.cartoes.cartao.presentation.dto.CartaoRequestDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cartao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartaoController {

    private CartaoService cartaoService;

    @Inject
    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }


    @POST
    public Response criarCartao(CartaoRequestDTO request) {
        cartaoService.cadastraCartao(request);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{idConta}")
    public Response listarCartoesCliente(@PathParam("idConta") Long idConta) {
        return Response.ok(cartaoService.listarCartoes(idConta)).build();
    }


    @GET
    @Path("/tracking/{idCartao}")
    public Response listarContasCliente(@PathParam("idCartao") Long idCartao) {
        return Response.ok(cartaoService.listarTracking(idCartao)).build();
    }


}

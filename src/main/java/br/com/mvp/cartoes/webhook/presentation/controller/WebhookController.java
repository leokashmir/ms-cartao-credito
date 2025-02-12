package br.com.mvp.cartoes.webhook.presentation.controller;

import br.com.mvp.cartoes.webhook.presentation.dto.CVVChangeWebhookDTO;
import br.com.mvp.cartoes.webhook.presentation.dto.DeliveryWebhookDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/webhooks")
public class WebhookController {


    @POST
    @Path("/delivery")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response handleDeliveryWebhook(@HeaderParam("X-API-KEY") String apiKey, DeliveryWebhookDTO deliveryWebhook) {
        System.out.println("Recebido webhook de entrega: " + deliveryWebhook);
        return Response.ok().build();
    }

    @POST
    @Path("/cvv-change")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response handleCVVChangeWebhook(@HeaderParam("X-API-KEY") String apiKey, CVVChangeWebhookDTO cvvChangeWebhook) {

        System.out.println("Recebido webhook de mudança de CVV: " + cvvChangeWebhook);
        return Response.ok().build();
    }
}

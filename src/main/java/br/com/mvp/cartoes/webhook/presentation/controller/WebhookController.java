package br.com.mvp.cartoes.webhook.presentation.controller;

import br.com.mvp.cartoes.webhook.presentation.application.WebhookService;
import br.com.mvp.cartoes.webhook.presentation.dto.CVVChangeWebhookDTO;
import br.com.mvp.cartoes.webhook.presentation.dto.DeliveryWebhookDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/webhooks")
public class WebhookController {

    private final WebhookService webhookService;

    @Inject
    public WebhookController(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @POST
    @Path("/delivery")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response handleDeliveryWebhook(@HeaderParam("X-API-KEY") String apiKey, DeliveryWebhookDTO deliveryWebhook) {
        webhookService.validarCartao(deliveryWebhook);
        return Response.ok().build();
    }

    @POST
    @Path("/cvv-change")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response handleCVVChangeWebhook(@HeaderParam("X-API-KEY") String apiKey, CVVChangeWebhookDTO cvvChangeWebhook) {
        webhookService.atualizarCvv(cvvChangeWebhook);
        return Response.ok().build();
    }
}

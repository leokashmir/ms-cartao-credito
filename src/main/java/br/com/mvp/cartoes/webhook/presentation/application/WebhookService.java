package br.com.mvp.cartoes.webhook.presentation.application;

import br.com.mvp.cartoes.cartao.application.service.CartaoService;
import br.com.mvp.cartoes.webhook.presentation.dto.CVVChangeWebhookDTO;
import br.com.mvp.cartoes.webhook.presentation.dto.DeliveryWebhookDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WebhookService {

    private CartaoService service;

    public WebhookService(CartaoService service) {
        this.service = service;
    }

    public void atualizarCvv(CVVChangeWebhookDTO cvvDto){
        service.atualizarCvvCartao(cvvDto.getAccountId(),
                cvvDto.getCardId(), String.valueOf(cvvDto.getNextCvv()), cvvDto.getExpirationDate());
    }

    public void validarCartao(DeliveryWebhookDTO deliveryWebhook) {
        service.validarCartao(deliveryWebhook.getTrackingId(),deliveryWebhook.getDeliveryStatus());
    }
}

package br.com.mvp.cartoes.webhook.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class DeliveryWebhookDTO {
    @JsonProperty("tracking_id")
    private String trackingId;

    @JsonProperty("delivery_status")
    private String deliveryStatus;

    @JsonProperty("delivery_date")
    private LocalDateTime deliveryDate;

    @JsonProperty("delivery_return_reason")
    private String deliveryReturnReason;

    @JsonProperty("delivery_address")
    private String deliveryAddress;

}



package br.com.mvp.cartoes.webhook.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CVVChangeWebhookDTO {

    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("card_id")
    private String cardId;

    @JsonProperty("next_cvv")
    private Integer nextCvv;

    @JsonProperty("expiration_date")
    private LocalDateTime expirationDate;
}

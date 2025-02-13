package br.com.mvp.cartoes.cartao.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContaPutRequest {

    private Long idCliente;
}

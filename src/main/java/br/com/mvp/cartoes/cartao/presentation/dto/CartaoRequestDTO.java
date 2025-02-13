package br.com.mvp.cartoes.cartao.presentation.dto;

import br.com.mvp.cartoes.cartao.domain.enuns.TipoCartao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class CartaoRequestDTO {

    private Long idConta;
    private Boolean ativo;
    private TipoCartao tipo;
}

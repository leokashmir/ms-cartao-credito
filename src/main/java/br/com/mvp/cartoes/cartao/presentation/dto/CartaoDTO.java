package br.com.mvp.cartoes.cartao.presentation.dto;

import br.com.mvp.cartoes.cartao.domain.enuns.TipoCartao;
import br.com.mvp.cartoes.cartao.domain.model.Conta;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @Builder
public class CartaoDTO {

    private Long idCartao;
    private String numCartao;
    private String cvv;
    private String titular;
    private LocalDate dtValidade;
    private TipoCartao tpCartao;
    private Conta conta;
    private Boolean ativo;
}

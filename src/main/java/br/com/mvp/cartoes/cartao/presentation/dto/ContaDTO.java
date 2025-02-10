package br.com.mvp.cartoes.cartao.presentation.dto;

import br.com.mvp.cartoes.cliente.domain.model.Cliente;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter @Builder
public class ContaDTO {


    private Long idConta;
    private String numeroConta;
    private Boolean ativo = true;
    private Cliente cliente;
}

package br.com.mvp.cartoes.cartao.presentation.dto;

import br.com.mvp.cartoes.cartao.domain.model.Cartao;
import br.com.mvp.cartoes.cliente.domain.model.Cliente;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter @Setter @Builder
public class ContaDTO {


    private Long idConta;
    private String numeroConta;
    private Boolean ativo = true;
    private Cliente cliente;
    private List<Cartao> cartoes;
}

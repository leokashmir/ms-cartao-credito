package br.com.mvp.cartoes.cliente.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ClienteRequestDTO {

    private Long idCliente;
    private String nome;
    private String documento;
    private String email;
    private List<EnderecoDTO> enderecos;
}

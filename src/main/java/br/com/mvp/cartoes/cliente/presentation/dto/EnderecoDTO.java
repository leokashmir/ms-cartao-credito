package br.com.mvp.cartoes.cliente.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Getter @Setter
public class EnderecoDTO {

    private Long idEndereco;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String codigoPostal;

}

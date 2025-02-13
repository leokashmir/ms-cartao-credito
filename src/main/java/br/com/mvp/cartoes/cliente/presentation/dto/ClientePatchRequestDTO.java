package br.com.mvp.cartoes.cliente.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ClientePatchRequestDTO {

    private Long idCliente;
    @NotNull(message = "O Nome é obrigatória")
    private String nome;

    @NotNull(message = "Documento é obrigatória")
    private String documento;

    @NotNull(message = "Email é obrigatória")
    @jakarta.validation.constraints.Email(message = "O e-mail deve ser válido")
    private String email;

    private List<EnderecoDTO> enderecos;
}

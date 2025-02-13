package br.com.mvp.cartoes.cliente.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ClientePostRequestDTO {


    @NotNull(message = "O Nome é obrigatória")
    private String nome;

    @NotNull(message = "Documento é obrigatória")
    private String documento;

    @NotNull(message = "Email é obrigatória")
    @jakarta.validation.constraints.Email(message = "O e-mail deve ser válido")
    private String email;

    private List<EnderecoDTO> enderecos;
}

package br.com.mvp.cartoes.cartao.domain.model;

import br.com.mvp.cartoes.cartao.domain.enuns.TipoCartao;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Cartao")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartao")
    private Long idCartao;

    @Column(name="num_cartao", unique = true, nullable = false)
    private String numCartao;

    @Column(name="nome", unique = true, nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cvv;

    @Column(name="dt_validade",nullable = false)
    private LocalDate dtValidade;

    @Enumerated(EnumType.STRING)
    @Column(name="tp_cartao")
    private TipoCartao tpCartao;

    private Boolean ativo;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
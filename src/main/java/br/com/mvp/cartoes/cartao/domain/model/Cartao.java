package br.com.mvp.cartoes.cartao.domain.model;

import br.com.mvp.cartoes.cartao.domain.enuns.TipoCartao;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Cartao")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartao")
    private Long idCartao;

    @Column(unique = true, nullable = false)
    private String numCartao;

    @Column(nullable = false)
    private String cvv;

    @Column(nullable = false)
    private LocalDate dtValidade;

    @Enumerated(EnumType.STRING)
    private TipoCartao tpCartao;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_conta", nullable = false)
    private Conta conta;

    private Boolean ativo = true;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
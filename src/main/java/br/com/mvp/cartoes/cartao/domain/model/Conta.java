package br.com.mvp.cartoes.cartao.domain.model;

import br.com.mvp.cartoes.cliente.domain.model.Cliente;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Conta")

public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Long idConta;

    @Column(name ="numero_conta", unique = true, nullable = false)
    private String numeroConta;

    private Boolean ativo;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @OneToMany
    @JsonBackReference
    @JoinColumn(name = "id_cartao", nullable = false)
    private List<Cartao> cartao;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
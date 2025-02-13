package br.com.mvp.cartoes.cartao.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "Tracking")
public class Tracking {

    @Id
    @Column(name = "tracking_id")
    private String trackingId;

    @Column(name = "delivery_status", nullable = false)
    private String deliveryStatus;

    @Column(name = "delivery_date", nullable = false)
    private LocalDateTime deliveryDate;

    @Column(name = "delivery_return_reason")
    private String deliveryReturnReason;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @ManyToOne
    @JoinColumn(name = "id_cartao", referencedColumnName = "id_cartao", nullable = false)
    private Cartao cartao;
}

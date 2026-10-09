package br.com.avaliacao.api_telemetria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_telemetria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricoTelemetria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motor_id", nullable = false)
    private Motor motor;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "temperatura_carcaca", precision = 5, scale = 2)
    private BigDecimal temperaturaCarcaca;

    @Column(name = "rpm_atual")
    private Integer rpmAtual;

    @Column(name = "vibracao_global", precision = 5, scale = 2)
    private BigDecimal vibracaoGlobal;

    @PrePersist
    public void prePersist() {
        if (this.dataHora == null) {
            this.dataHora = LocalDateTime.now();
        }
    }
}

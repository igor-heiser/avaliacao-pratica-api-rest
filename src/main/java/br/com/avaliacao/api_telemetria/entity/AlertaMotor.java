package br.com.avaliacao.api_telemetria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "alertas_motores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertaMotor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motor_id", nullable = false)
    private Motor motor;

    @Column(name = "data_alerta")
    private LocalDateTime dataAlerta;

    @Column(name = "tipo_anomalia", length = 100)
    private String tipoAnomalia;

    @Column(length = 20)
    private String criticidade;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Builder.Default
    private Boolean resolvido = false;

    @PrePersist
    public void prePersist() {
        if (this.dataAlerta == null) {
            this.dataAlerta = LocalDateTime.now();
        }
    }
}

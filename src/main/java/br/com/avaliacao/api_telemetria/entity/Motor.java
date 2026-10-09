package br.com.avaliacao.api_telemetria.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "motores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Motor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setor_id")
    private Setor setor;

    @Column(name = "codigo_ativo", nullable = false, unique = true, length = 50)
    private String codigoAtivo;

    private String fabricante;
    private String modelo;

    @Column(name = "potencia_kw", precision = 6, scale = 2)
    private BigDecimal potenciaKw;

    @Column(name = "rpm_nominal")
    private Integer rpmNominal;

    @Column(name = "data_instalacao")
    private LocalDate dataInstalacao;

    @Column(name = "status_atual", length = 30)
    private String statusAtual;
}

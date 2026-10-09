package br.com.avaliacao.api_telemetria.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "setores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Setor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 100)
    private String localizacao;
}

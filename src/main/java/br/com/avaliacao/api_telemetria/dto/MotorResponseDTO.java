package br.com.avaliacao.api_telemetria.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Dados retornados pelo API referente a um motor cadastrado.
 * @param id identificador único do motor
 * @param setorId identificador único do setor que faz relacionamento com o motor
 * @param setorNome nome do setor
 * @param codigoAtivo código ativo do motor
 * @param fabricante fabricante do motor
 * @param modelo modelo do motor
 * @param potenciaKw potência em Kw do motor
 * @param rpmNominal rpmNominal do motor
 * @param dataInstalacao data em que foi feita a instalação do motor
 * @param statusAtual situação atual em que o motor se encontra
 */
@Schema(description = "Dados retornados pelo API referente a um motor cadastrado")
public record MotorResponseDTO(
        @Schema(
                description = "Identificador único do motor",
                example = "22"
        )
        Integer id,

        @Schema(
                description = "Identificador único do setor que faz relacionamento com o motor",
                example = "22"
        )
        Integer setorId,

        @Schema(
                description = "Nome do setor",
                example = "Fábrica"
        )
        String setorNome,

        @Schema(
                description = "Código ativo do motor",
                example = "MOT-USI-001"
        )
        String codigoAtivo,

        @Schema(
                description = "Fabricante do motor",
                example = "WEG"
        )
        String fabricante,

        @Schema(
                description = "Modelo do motor",
                example = "W22 Premium"
        )
        String modelo,

        @Schema(
                description = "Potência em Kw do motor",
                example = "15.50"
        )
        BigDecimal potenciaKw,

        @Schema(
                description = "RpmNominal do motor",
                example = "1800"
        )
        Integer rpmNominal,

        @Schema(
                description = "Data em que foi feita a instalação do motor",
                example = "2023-01-15"
        )
        LocalDate dataInstalacao,

        @Schema(
                description = "Situação atual em que o motor se encontra",
                example = "EM_OPERACAO"
        )
        String statusAtual
) {
}

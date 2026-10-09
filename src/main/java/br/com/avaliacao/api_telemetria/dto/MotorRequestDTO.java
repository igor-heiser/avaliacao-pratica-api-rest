package br.com.avaliacao.api_telemetria.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Dados necessários para cadastrar um novo motor.
 * @param setorId identificador único do setor que faz relacionamento com o motor
 * @param codigoAtivo código ativo do motor
 * @param fabricante fabricante do motor
 * @param modelo modelo do motor
 * @param potenciaKw potência em Kw do motor
 * @param rpmNominal RpmNominal do motor
 * @param dataInstalacao data em que foi feita a instalação do motor
 * @param statusAtual situação atual em que o motor se encontra
 */
@Schema(description = "Dados necessários para cadastrar um novo motor")
public record MotorRequestDTO(
        @Schema(
                description = "Identificador único do setor que faz relacionamento com o motor",
                example = "22"
        )
        Integer setorId,

        @Schema(
                description = "Código ativo do motor",
                example = "MOT-USI-001"
        )
        @NotBlank(message = "O código do ativo é obrigatório")
        @Size(max = 50, message = "O código do ativo deve ter no máximo 50 caracteres")
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
        @Positive(message = "A potência deve ser um valor positivo")
        BigDecimal potenciaKw,

        @Schema(
                description = "RpmNominal do motor",
                example = "1800"
        )
        @Positive(message = "O RPM nominal deve ser um valor positivo")
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

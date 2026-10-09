package br.com.avaliacao.api_telemetria.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Dados retornados pela API referente a uma telemetria cadastrada
 * @param id identificador único da telemetria
 * @param motorId identificador único do motor que faz relacionamento com uma telemetria
 * @param dataHora data e hora em que foi feita a telemtria
 * @param temperaturaCarcaca temperatura da carcaça do motor
 * @param rpmAtual rpm atual do motor
 * @param vibracaoGlobal vibração global do motor
 */
@Schema(description = "Dados retornados pela API referente a uma telemetria cadastrada")
public record TelemetriaResponseDTO(
        @Schema(
                description = "Identificador único da telemetria",
                example = "22"
        )
        Long id,

        @Schema(
                description = "Identificador único do motor",
                example = "22"
        )
        Integer motorId,

        @Schema(
                description = "Data e hora em que foi feita a telemtria",
                example = "2026-10-07T08:05:00"
        )
        LocalDateTime dataHora,

        @Schema(
                description = "Temperatura da carcaça do motor",
                example = "45.20"
        )
        BigDecimal temperaturaCarcaca,

        @Schema(
                description = "Rpm atual do motor",
                example = "1795"
        )
        Integer rpmAtual,

        @Schema(
                description = "Vibração global do motor",
                example = "2.10"
        )
        BigDecimal vibracaoGlobal
) { }
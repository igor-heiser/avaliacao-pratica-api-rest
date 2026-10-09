package br.com.avaliacao.api_telemetria.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Dados necessários para cadastrar uma nova telemetria
 * @param motorId identificador único do motor que faz relacionamento com a telemetria
 * @param temperaturaCarcaca temperatura da carcaça do motor
 * @param rpmAtual rpm atual do motor
 * @param vibracaoGlobal vibração global do motor
 */
@Schema(description = "Dados necessários para cadastrar uma nova telemetria")
public record TelemetriaRequestDTO(
        @Schema(
                description = "Identificador único do motor",
                example = "22"
        )
        @NotNull(message = "O ID do motor é obrigatório")
        Integer motorId,

        @Schema(
                description = "Temperatura da carcaça do motor",
                example = "45.20"
        )
        @NotNull(message = "A temperatura é obrigatória")
        BigDecimal temperaturaCarcaca,

        @Schema(
                description = "Rpm atual do motor",
                example = "1795"
        )
        @NotNull(message = "O RPM é obrigatório")
        Integer rpmAtual,

        @Schema(
                description = "Vibração global do motor",
                example = "2.10"
        )
        @NotNull(message = "A vibração é obrigatória")
        BigDecimal vibracaoGlobal
) {
}

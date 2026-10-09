package br.com.avaliacao.api_telemetria.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * Dados necessários para atualizar o status de um motor.
 * @param statusAtual situação atual em que um motor se encontra
 */
@Schema(description = "Dados necessários para atualizar o status de um motor")
public record MotorStatusUpdateDTO(
        @Schema(
                description = "Situação atual em que o motor se encontra",
                example = "EM_OPERACAO"
        )
        @NotBlank(message = "O status atual é obrigatório")
        String statusAtual
) {
}

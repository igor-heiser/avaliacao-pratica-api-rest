package br.com.avaliacao.api_telemetria.dto;

import jakarta.validation.constraints.NotBlank;

public record MotorStatusUpdateDTO(
        @NotBlank(message = "O status atual é obrigatório")
        String statusAtual
) {
}

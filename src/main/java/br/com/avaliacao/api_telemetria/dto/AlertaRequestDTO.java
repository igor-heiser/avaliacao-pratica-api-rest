package br.com.avaliacao.api_telemetria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AlertaRequestDTO(
        @NotNull(message = "O ID do motor é obrigatório")
        Integer motorId,

        @NotBlank(message = "O tipo de anomalia é obrigatório")
        @Size(max = 100, message = "O tipo de anomalia deve ter no máximo 100 caracteres")
        String tipoAnomalia,

        @NotBlank(message = "A criticidade é obrigatória")
        @Size(max = 20, message = "A criticidade deve ter no máximo 20 caracteres")
        String criticidade,

        String descricao
) {
}

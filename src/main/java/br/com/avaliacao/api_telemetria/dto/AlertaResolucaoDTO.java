package br.com.avaliacao.api_telemetria.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * Dados necessários para atualizar a resolução de um alerta.
 * @param resolvido situação atual da resolução de um alerta
 */
@Schema(description = "Dados necessários para atualizar a resolução de um alerta")
public record AlertaResolucaoDTO(
        @Schema(
                description = "Situação atual da resolução de um alerta",
                example = "true"
        )
        @NotNull(message = "O status de resolução é obrigatório")
        Boolean resolvido
) { }
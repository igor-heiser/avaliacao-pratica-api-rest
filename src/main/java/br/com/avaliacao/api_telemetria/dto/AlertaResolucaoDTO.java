package br.com.avaliacao.api_telemetria.dto;

import jakarta.validation.constraints.NotNull;

public record AlertaResolucaoDTO(
        @NotNull(message = "O status de resolução é obrigatório")
        Boolean resolvido
) { }
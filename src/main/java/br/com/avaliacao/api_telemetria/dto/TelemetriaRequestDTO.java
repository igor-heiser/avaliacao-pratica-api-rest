package br.com.avaliacao.api_telemetria.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record TelemetriaRequestDTO(
        @NotNull(message = "O ID do motor é obrigatório")
        Integer motorId,

        @NotNull(message = "A temperatura é obrigatória")
        BigDecimal temperaturaCarcaca,

        @NotNull(message = "O RPM é obrigatório")
        Integer rpmAtual,

        @NotNull(message = "A vibração é obrigatória")
        BigDecimal vibracaoGlobal
) {
}

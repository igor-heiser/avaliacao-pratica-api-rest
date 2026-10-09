package br.com.avaliacao.api_telemetria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MotorRequestDTO(
        Integer setorId,

        @NotBlank(message = "O código do ativo é obrigatório")
        @Size(max = 50, message = "O código do ativo deve ter no máximo 50 caracteres")
        String codigoAtivo,

        String fabricante,
        String modelo,

        @Positive(message = "A potência deve ser um valor positivo")
        BigDecimal potenciaKw,

        @Positive(message = "O RPM nominal deve ser um valor positivo")
        Integer rpmNominal,

        LocalDate dataInstalacao,
        String statusAtual
) {
}

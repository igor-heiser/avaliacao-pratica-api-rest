package br.com.avaliacao.api_telemetria.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MotorResponseDTO(
        Integer id,
        Integer setorId,
        String setorNome,
        String codigoAtivo,
        String fabricante,
        String modelo,
        BigDecimal potenciaKw,
        Integer rpmNominal,
        LocalDate dataInstalacao,
        String statusAtual
) {
}

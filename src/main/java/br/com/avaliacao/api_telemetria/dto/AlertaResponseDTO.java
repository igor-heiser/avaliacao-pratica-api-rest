package br.com.avaliacao.api_telemetria.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Dados retornados pela API referente a um alerta cadastrado.
 * @param id identificador único do alerta
 * @param motorId identificador único do motor que faz relacionamento com o alerta
 * @param codigoAtivoMotor código ativo do motor
 * @param dataAlerta data em que o alerta foi criado ou atualizado
 * @param tipoAnomalia tipo da anomalia do alerta
 * @param criticidade nível de criticidade do alerta
 * @param descricao descrição sobre o alerta
 * @param resolvido situação atual da resolução de um alerta
 */
@Schema(description = "Dados retornados pela API referente a um alerta cadastrado")
public record AlertaResponseDTO(
        @Schema(
                description = "Identificador único do alerta",
                example = "22"
        )
        Integer id,

        @Schema(
                description = "Identificador único do motor",
                example = "22"
        )
        Integer motorId,

        @Schema(
                description = "Código ativo do motor",
                example = "MOT-USI-001"
        )
        String codigoAtivoMotor,

        @Schema(
                description = "Data em que o alerta foi criado ou atualizado",
                example = "2026-10-09T19:21:23.822121"
        )
        LocalDateTime dataAlerta,

        @Schema(
                description = "Tipo da anomalia do alerta",
                example = "Vibração excessiva"
        )
        String tipoAnomalia,

        @Schema(
                description = "Nivel de criticidade do alerta",
                example = "ALTA"
        )
        String criticidade,

        @Schema(
                description = "Descrição sobre o alerta",
                example = "Sensor detectou pico de vibração no mancal dianteiro."
        )
        String descricao,

        @Schema(
                description = "Situação atual da resolução de um alerta",
                example = "false"
        )
        Boolean resolvido
) {
}

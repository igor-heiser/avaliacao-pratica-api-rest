package br.com.avaliacao.api_telemetria.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Dados necessários para cadastrar um novo alerta.
 * @param motorId identificador único do motor que faz relacionamento com o alerta
 * @param tipoAnomalia tipo da anomalia do alerta
 * @param criticidade nivel de criticidade do alerta
 * @param descricao descrição sobre o alerta
 */
@Schema(description = "Dados necessários para cadastrar um novo alerta")
public record AlertaRequestDTO(
        @Schema(
                description = "Identificador único do motor",
                example = "22"
        )
        @NotNull(message = "O ID do motor é obrigatório")
        Integer motorId,

        @Schema(
                description = "Tipo da anomalia do alerta",
                example = "Vibração excessiva"
        )
        @NotBlank(message = "O tipo de anomalia é obrigatório")
        @Size(max = 100, message = "O tipo de anomalia deve ter no máximo 100 caracteres")
        String tipoAnomalia,

        @Schema(
                description = "Nivel de criticidade do alerta",
                example = "ALTA"
        )
        @NotBlank(message = "A criticidade é obrigatória")
        @Size(max = 20, message = "A criticidade deve ter no máximo 20 caracteres")
        String criticidade,

        @Schema(
                description = "Descrição sobre o alerta",
                example = "Sensor detectou pico de vibração no mancal dianteiro."
        )
        String descricao
) {
}

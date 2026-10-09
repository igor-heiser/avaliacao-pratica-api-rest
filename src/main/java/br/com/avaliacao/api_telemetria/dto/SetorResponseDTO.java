package br.com.avaliacao.api_telemetria.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Dados retornados pela API referente a um setor cadastrado
 * @param id identificador único do setor
 * @param nome nome do setor
 * @param localizacao localização atual do setor
 */
@Schema(description = "Dados retornados pela API referente a um setor cadastrado")
public record SetorResponseDTO(
        @Schema(
                description = "Identificador único do setor",
                example = "22"
        )
        Integer id,

        @Schema(
                description = "Nome do setor",
                example = "Prensa e Estamparia"
        )
        String nome,

        @Schema(
                description = "Localização atual do setor",
                example = "Galpão B - Setor Sul"
        )
        String localizacao
) {
}

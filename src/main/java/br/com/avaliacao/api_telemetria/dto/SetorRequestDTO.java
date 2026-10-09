package br.com.avaliacao.api_telemetria.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Dados necessários para cadastrar um novo setor.
 * @param nome nome do setor
 * @param localizacao localização atual do setor
 */
@Schema(description = "Dados necessários para cadastrar um novo setor")
public record SetorRequestDTO (
        @Schema(
                description = "Nome do setor",
                example = "Prensa e Estamparia"
        )
        @NotBlank(message = "O nome do setor é obrigatório")
        @Size(max = 100, message = "O nome do setor deve ter no máximo 100 caracteres")
        String nome,

        @Schema(
                description = "Localização atual do setor",
                example = "Galpão B - Setor Sul"
        )
        @Size(max = 100, message = "A localização deve ter no máximo 100 caracteres")
        String localizacao
){
}
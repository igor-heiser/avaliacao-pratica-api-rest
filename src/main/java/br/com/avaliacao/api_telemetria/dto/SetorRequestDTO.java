package br.com.avaliacao.api_telemetria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SetorRequestDTO (
        @NotBlank(message = "O nome do setor é obrigatório")
        @Size(max = 100, message = "O nome do setor deve ter no máximo 100 caracteres")
        String nome,

        @Size(max = 100, message = "A localização deve ter no máximo 100 caracteres")
        String localizacao
){

}

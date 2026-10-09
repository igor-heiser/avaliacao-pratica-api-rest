package br.com.avaliacao.api_telemetria.mapper;

import br.com.avaliacao.api_telemetria.dto.SetorRequestDTO;
import br.com.avaliacao.api_telemetria.dto.SetorResponseDTO;
import br.com.avaliacao.api_telemetria.entity.Setor;
import org.springframework.stereotype.Component;

@Component
public class SetorMapper {

    public Setor toEntity(SetorRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return Setor.builder()
                .nome(dto.nome())
                .localizacao(dto.localizacao())
                .build();
    }

    public SetorResponseDTO toDTO(Setor entity) {
        if (entity == null) {
            return null;
        }

        return new SetorResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getLocalizacao()
        );
}}

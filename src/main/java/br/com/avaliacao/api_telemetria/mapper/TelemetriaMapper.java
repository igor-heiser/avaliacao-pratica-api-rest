package br.com.avaliacao.api_telemetria.mapper;

import br.com.avaliacao.api_telemetria.dto.TelemetriaRequestDTO;
import br.com.avaliacao.api_telemetria.dto.TelemetriaResponseDTO;
import br.com.avaliacao.api_telemetria.entity.HistoricoTelemetria;
import br.com.avaliacao.api_telemetria.entity.Motor;
import org.springframework.stereotype.Component;

@Component
public class TelemetriaMapper {
    public HistoricoTelemetria toEntity(TelemetriaRequestDTO dto, Motor motor) {
        if (dto == null) {
            return null;
        }

        return HistoricoTelemetria.builder()
                .motor(motor)
                .temperaturaCarcaca(dto.temperaturaCarcaca())
                .rpmAtual(dto.rpmAtual())
                .vibracaoGlobal(dto.vibracaoGlobal())
                .build();
    }

    public TelemetriaResponseDTO toDTO(HistoricoTelemetria entity) {
        if (entity == null) {
            return null;
        }

        Integer motorId = (entity.getMotor() != null) ? entity.getMotor().getId() : null;

        return new TelemetriaResponseDTO(
                entity.getId(),
                motorId,
                entity.getDataHora(),
                entity.getTemperaturaCarcaca(),
                entity.getRpmAtual(),
                entity.getVibracaoGlobal()
        );
    }
}

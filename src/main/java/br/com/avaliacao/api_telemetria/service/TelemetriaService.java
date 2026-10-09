package br.com.avaliacao.api_telemetria.service;

import br.com.avaliacao.api_telemetria.dto.TelemetriaRequestDTO;
import br.com.avaliacao.api_telemetria.dto.TelemetriaResponseDTO;
import br.com.avaliacao.api_telemetria.entity.HistoricoTelemetria;
import br.com.avaliacao.api_telemetria.entity.Motor;
import br.com.avaliacao.api_telemetria.mapper.TelemetriaMapper;
import br.com.avaliacao.api_telemetria.repository.HistoricoTelemetriaRepository;
import br.com.avaliacao.api_telemetria.repository.MotorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TelemetriaService {
    private final HistoricoTelemetriaRepository telemetriaRepository;
    private final MotorRepository motorRepository;
    private final TelemetriaMapper telemetriaMapper;

    public TelemetriaService(HistoricoTelemetriaRepository telemetriaRepository, MotorRepository motorRepository, TelemetriaMapper telemetriaMapper) {
        this.telemetriaRepository = telemetriaRepository;
        this.motorRepository = motorRepository;
        this.telemetriaMapper = telemetriaMapper;
    }

    @Transactional
    public TelemetriaResponseDTO registrar(TelemetriaRequestDTO dto) {
        Motor motor = motorRepository.findById(dto.motorId())
                .orElseThrow(() -> new EntityNotFoundException("Motor não encontrado com ID: " + dto.motorId()));

        HistoricoTelemetria telemetria = telemetriaMapper.toEntity(dto, motor);
        HistoricoTelemetria salva = telemetriaRepository.save(telemetria);

        return telemetriaMapper.toDTO(salva);
    }

    @Transactional(readOnly = true)
    public Page<TelemetriaResponseDTO> listarTodos(Pageable pageable) {
        return telemetriaRepository.findAll(pageable)
                .map(telemetriaMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public TelemetriaResponseDTO buscarPorId(Long id) {
        return telemetriaRepository.findById(id)
                .map(telemetriaMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Registro de telemetria não encontrado para o ID: " + id));
    }

    @Transactional(readOnly = true)
    public Page<TelemetriaResponseDTO> buscarPorMotor(Integer motorId, Pageable pageable) {
        if (!motorRepository.existsById(motorId)) {
            throw new EntityNotFoundException("Motor não encontrado com ID: " + motorId);
        }
        return telemetriaRepository.findByMotorIdOrderByDataHoraDesc(motorId, pageable)
                .map(telemetriaMapper::toDTO);
    }
}

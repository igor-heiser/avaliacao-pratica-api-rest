package br.com.avaliacao.api_telemetria.service;

import br.com.avaliacao.api_telemetria.dto.AlertaRequestDTO;
import br.com.avaliacao.api_telemetria.dto.AlertaResolucaoDTO;
import br.com.avaliacao.api_telemetria.dto.AlertaResponseDTO;
import br.com.avaliacao.api_telemetria.entity.AlertaMotor;
import br.com.avaliacao.api_telemetria.entity.Motor;
import br.com.avaliacao.api_telemetria.mapper.AlertaMapper;
import br.com.avaliacao.api_telemetria.repository.AlertaMotorRepository;
import br.com.avaliacao.api_telemetria.repository.MotorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Serviço responsável pelas válidações e regras de negócio referente aos alertas.
 */
@Service
public class AlertaMotorService {
    private final AlertaMotorRepository alertaRepository;
    private final MotorRepository motorRepository;
    private final AlertaMapper alertaMapper;

    public AlertaMotorService(AlertaMotorRepository alertaRepository, MotorRepository motorRepository, AlertaMapper alertaMapper) {
        this.alertaRepository = alertaRepository;
        this.motorRepository = motorRepository;
        this.alertaMapper = alertaMapper;
    }

    /**
     * Cria um novo alerta após validar se o identificador único do motor existe.
     * @param dto requisição recebida com os dados necessários para cadastrar um alerta
     * @return retorna um DTO de resposta com os dados do alerta criado
     */
    @Transactional
    public AlertaResponseDTO criar(AlertaRequestDTO dto) {
        Motor motor = motorRepository.findById(dto.motorId())
                .orElseThrow(() -> new EntityNotFoundException("Motor não encontrado para o ID: " + dto.motorId()));

        AlertaMotor alerta = alertaMapper.toEntity(dto, motor);
        return alertaMapper.toDTO(alertaRepository.save(alerta));
    }

    /**
     * Lista todos os alertas cadastrados
     * @param pageable paginação recebida
     * @return retorna todos os alertas cadastrados
     */
    @Transactional(readOnly = true)
    public Page<AlertaResponseDTO> listarTodos(Pageable pageable) {
        return alertaRepository.findAll(pageable)
                .map(alertaMapper::toDTO);
    }

    /**
     * Lista todos os alertas por status de suas resoluções
     * @param resolvido parâmetro recebido para filtrar o status de resolução dos alertas
     * @param pageable paginação recebida
     * @return retorna todos os alertas filtrados por status de resolução
     */
    @Transactional(readOnly = true)
    public Page<AlertaResponseDTO> listarPorStatusResolucao(Boolean resolvido, Pageable pageable) {
        return alertaRepository.findByResolvidoOrderByDataAlertaDesc(resolvido, pageable)
                .map(alertaMapper::toDTO);
    }

    /**
     * Lista um alerta pelo identificador único de um motor após valdiar a existência do identificador único do motor.
     * @param motorId identificador único do motor a ser buscado
     * @param pageable paginação recebida
     * @return retorna o alerta que possui o identificador único do motor informado
     */
    @Transactional(readOnly = true)
    public Page<AlertaResponseDTO> buscarPorMotor(Integer motorId, Pageable pageable) {
        if (!motorRepository.existsById(motorId)) {
            throw new EntityNotFoundException("Motor não encontrado para o ID: " + motorId);
        }
        return alertaRepository.findByMotorIdOrderByDataAlertaDesc(motorId, pageable)
                .map(alertaMapper::toDTO);
    }

    /**
     * Atualiza o status de resolução de um alerta pelo seu identificador único após validar a existência do seu identificador único.
     * @param id identificador único do alerta a ser atualizado
     * @param dto requisição recebida com o dado necessário para atualizar o status de resolução de um alerta
     * @return retorna um DTO de resposta com os dados do alerta atualizado
     */
    @Transactional
    public AlertaResponseDTO atualizarStatusResolucao(Integer id, AlertaResolucaoDTO dto) {
        AlertaMotor alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alerta não encontrado para o ID: " + id));

        alerta.setResolvido(dto.resolvido());
        return alertaMapper.toDTO(alertaRepository.save(alerta));
    }
}

package br.com.avaliacao.api_telemetria.service;

import br.com.avaliacao.api_telemetria.dto.MotorRequestDTO;
import br.com.avaliacao.api_telemetria.dto.MotorResponseDTO;
import br.com.avaliacao.api_telemetria.dto.MotorStatusUpdateDTO;
import br.com.avaliacao.api_telemetria.entity.Motor;
import br.com.avaliacao.api_telemetria.entity.Setor;
import br.com.avaliacao.api_telemetria.mapper.MotorMapper;
import br.com.avaliacao.api_telemetria.repository.MotorRepository;
import br.com.avaliacao.api_telemetria.repository.SetorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MotorService {
    private final MotorRepository motorRepository;
    private final SetorRepository setorRepository;
    private final MotorMapper motorMapper;

    public MotorService(MotorRepository motorRepository, SetorRepository setorRepository, MotorMapper motorMapper) {
        this.motorRepository = motorRepository;
        this.setorRepository = setorRepository;
        this.motorMapper = motorMapper;
    }

    @Transactional
    public MotorResponseDTO criar(MotorRequestDTO dto) {
        if (motorRepository.existsByCodigoAtivo(dto.codigoAtivo())) {
            throw new IllegalArgumentException("Já existe um motor cadastrado com o código do ativo: " + dto.codigoAtivo());
        }

        Setor setor = buscarSetorSeInformado(dto.setorId());
        Motor motor = motorMapper.toEntity(dto, setor);

        return motorMapper.toDTO(motorRepository.save(motor));
    }

    @Transactional(readOnly = true)
    public Page<MotorResponseDTO> listarTodos(Pageable pageable) {
        return motorRepository.findAll(pageable)
                .map(motorMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public MotorResponseDTO buscarPorId(Integer id) {
        return motorRepository.findById(id)
                .map(motorMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Motor não encontrado para o ID: " + id));
    }

    @Transactional(readOnly = true)
    public MotorResponseDTO buscarPorCodigoAtivo(String codigoAtivo) {
        return motorRepository.findByCodigoAtivo(codigoAtivo)
                .map(motorMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Motor não encontrado para o código: " + codigoAtivo));
    }

    @Transactional
    public MotorResponseDTO atualizar(Integer id, MotorRequestDTO dto) {
        Motor motor = motorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Motor não encontrado para o ID: " + id));

        if (motorRepository.existsByCodigoAtivoAndIdNot(dto.codigoAtivo(), id)) {
            throw new IllegalArgumentException("O código de ativo informado já está em uso por outro motor.");
        }

        Setor setor = buscarSetorSeInformado(dto.setorId());

        motor.setSetor(setor);
        motor.setCodigoAtivo(dto.codigoAtivo());
        motor.setFabricante(dto.fabricante());
        motor.setModelo(dto.modelo());
        motor.setPotenciaKw(dto.potenciaKw());
        motor.setRpmNominal(dto.rpmNominal());
        motor.setDataInstalacao(dto.dataInstalacao());
        if (dto.statusAtual() != null) {
            motor.setStatusAtual(dto.statusAtual());
        }

        return motorMapper.toDTO(motorRepository.save(motor));
    }

    @Transactional
    public MotorResponseDTO atualizarStatus(Integer id, MotorStatusUpdateDTO dto) {
        Motor motor = motorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Motor não encontrado para o ID: " + id));

        motor.setStatusAtual(dto.statusAtual());
        return motorMapper.toDTO(motorRepository.save(motor));
    }

    private Setor buscarSetorSeInformado(Integer setorId) {
        if (setorId == null) {
            return null;
        }
        return setorRepository.findById(setorId)
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado para o ID: " + setorId));
    }
}

package br.com.avaliacao.api_telemetria.service;

import br.com.avaliacao.api_telemetria.dto.SetorRequestDTO;
import br.com.avaliacao.api_telemetria.dto.SetorResponseDTO;
import br.com.avaliacao.api_telemetria.entity.Setor;
import br.com.avaliacao.api_telemetria.mapper.SetorMapper;
import br.com.avaliacao.api_telemetria.repository.SetorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Serviço responsável pela manipulação dos dados de um setor e validação regras de negócio.
 */
@Service
public class SetorService {
    private final SetorRepository setorRepository;
    private final SetorMapper setorMapper;

    public SetorService(SetorRepository setorRepository, SetorMapper setorMapper) {
        this.setorRepository = setorRepository;
        this.setorMapper = setorMapper;
    }

    /**
     * Cadastra um setor novo setor validando se já existe algum setor com o mesmo nome.
     * @param dto requisição recebida com os dados necessários para cadastrar um setor
     * @return retorna um DTO de resposta com os dados do setor cadastrado
     */
    @Transactional
    public SetorResponseDTO criar(SetorRequestDTO dto) {
        if (setorRepository.existsByNomeIgnoreCase(dto.nome())) {
            throw new IllegalArgumentException("Já existe um setor cadastrado com o nome: " + dto.nome());
        }

        Setor setor = setorMapper.toEntity(dto);
        return setorMapper.toDTO(setorRepository.save(setor));
    }

    @Transactional(readOnly = true)
    public Page<SetorResponseDTO> listarTodos(Pageable pageable) {
        return setorRepository.findAll(pageable)
                .map(setorMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public SetorResponseDTO buscarPorId(Integer id) {
        return setorRepository.findById(id)
                .map(setorMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado para o ID: " + id));
    }

    @Transactional
    public SetorResponseDTO atualizar(Integer id, SetorRequestDTO dto) {
        Setor setor = setorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado para o ID: " + id));

        if (setorRepository.existsByNomeIgnoreCaseAndIdNot(dto.nome(), id)) {
            throw new IllegalArgumentException("Já existe outro setor cadastrado com o nome: " + dto.nome());
        }

        setor.setNome(dto.nome());
        setor.setLocalizacao(dto.localizacao());

        return setorMapper.toDTO(setorRepository.save(setor));
    }
}

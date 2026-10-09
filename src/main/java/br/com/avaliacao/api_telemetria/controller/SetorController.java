package br.com.avaliacao.api_telemetria.controller;

import br.com.avaliacao.api_telemetria.dto.SetorRequestDTO;
import br.com.avaliacao.api_telemetria.dto.SetorResponseDTO;
import br.com.avaliacao.api_telemetria.service.SetorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Controller REST responsável pelo gerenciamento de setores.
 */
@RestController
@RequestMapping("/api-telemetria/v1/setores")
@Tag(
        name = "Setores",
        description = "Gerenciamento dos setores"
)
public class SetorController {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    /**
     * Cadastra um novo setor.
     * @param requestDTO requisição recebida com os dados necessários para cadastrar um novo setor
     * @return retorna um DTO de resposta com o recurso e cabeçalho Location
     */
    @Operation(
            summary = "Cadastrar um cliente",
            description = "Cadastra um novo cliente e retorna o recurso com o Location"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Setor cadastrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados ou requisição inválida"
            )
    })
    @PostMapping
    public ResponseEntity<SetorResponseDTO> criar(@Valid @RequestBody SetorRequestDTO requestDTO) {
        SetorResponseDTO setorResponseDTO = setorService.criar(requestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{setorId}")
                .buildAndExpand(setorResponseDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(setorResponseDTO);
    }

    /**
     * Lista todos os setores
     * @param pageable
     * @return retorna todos os setores cadastrados
     */
    @Operation(
            summary = "Lista os clientes",
            description = "Lista todos os clientes cadastrados"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Setores listados"
            )
    })
    @GetMapping
    public ResponseEntity<Page<SetorResponseDTO>> listarTodos(@PageableDefault Pageable pageable){
        Page<SetorResponseDTO> setorResponseDTOPageable = setorService.listarTodos(pageable);

        return ResponseEntity.ok().body(setorResponseDTOPageable);
    }

    @Operation(
            summary = "Busca um setor pelo seu identificador único",
            description = "Busca e retorna um setor pelo seu identificador único"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Setor encontrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Setor não encontrado com o ID informado"
            )
    })
    @GetMapping("/{setorId}")
    public ResponseEntity<SetorResponseDTO> buscarPorId(
            @PathVariable
            @Parameter(
                    description = "Identificador único do setor",
                    example = "1")
            Integer setorId){

        return ResponseEntity.ok().body(setorService.buscarPorId(setorId));
    }

    @Operation(
            summary = "Atualiza um setor",
            description = "Atualiza um setor buscando ele pelo seu identificador único informado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Setor atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados ou requisição inválida"

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Setor não encontrado com o ID informado"
            )
    })
    @PatchMapping("/{setorId}")
    public ResponseEntity<SetorResponseDTO> atualizar(
            @PathVariable
            @Parameter(
                    description = "Identificador único do setor",
                    example = "1"
            )
            Integer setorId,
            @Valid @RequestBody SetorRequestDTO requestDTO){

        return ResponseEntity.ok().body(setorService.atualizar(setorId, requestDTO));
    }
}
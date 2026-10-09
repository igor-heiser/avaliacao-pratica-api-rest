package br.com.avaliacao.api_telemetria.controller;

import br.com.avaliacao.api_telemetria.dto.SetorRequestDTO;
import br.com.avaliacao.api_telemetria.dto.SetorResponseDTO;
import br.com.avaliacao.api_telemetria.service.SetorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api-telemetria/v1/setores")
@Tag(
        name = "Setores",
        description = "Endpoints referentes ao gerenciamento de setores"
)
public class SetorController {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @Operation(
            summary = "Cadastrar um setor",
            description = "Cadastra um novo setor e retorna o recurso com Location"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Setor cadastrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Um ou mais campos da requisição são inválidos"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
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

    @Operation(
            summary = "Lista os setores",
            description = "Lista todos os setores cadastrados"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Setores listados com sucesso"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @GetMapping
    public ResponseEntity<Page<SetorResponseDTO>> listarTodos(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){

        return ResponseEntity.ok().body(setorService.listarTodos(pageable));
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
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @GetMapping("/{setorId}")
    public ResponseEntity<SetorResponseDTO> buscarPorId(
            @PathVariable
            @Parameter(description = "Identificador único do setor", example = "1")
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
                    description = "Um ou mais campos da requisição são inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Setor não encontrado com o ID informado"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @PutMapping("/atualizar/{setorId}")
    public ResponseEntity<SetorResponseDTO> atualizar(
            @PathVariable
            @Parameter(description = "Identificador único do setor", example = "1")
            Integer setorId,
            @Valid @RequestBody SetorRequestDTO requestDTO){

        return ResponseEntity.ok().body(setorService.atualizar(setorId, requestDTO));
    }
}
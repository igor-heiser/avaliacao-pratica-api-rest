package br.com.avaliacao.api_telemetria.controller;

import br.com.avaliacao.api_telemetria.dto.MotorRequestDTO;
import br.com.avaliacao.api_telemetria.dto.MotorResponseDTO;
import br.com.avaliacao.api_telemetria.dto.MotorStatusUpdateDTO;
import br.com.avaliacao.api_telemetria.service.MotorService;
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
@RequestMapping("/api-telemetria/v1/motores")
@Tag(
        name = "Motores",
        description = "Endpoints referentes ao gerenciamento de motores"
)
public class MotorController {

    private final MotorService motorService;

    public MotorController(MotorService motorService) {
        this.motorService = motorService;
    }

    @Operation(
            summary = "Cadastrar um motor",
            description = "Cadastra um novo motor e retorna o recurso com Location"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Motor cadastrado com sucesso"
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
    public ResponseEntity<MotorResponseDTO> criar(@Valid @RequestBody MotorRequestDTO requestDTO) {
        MotorResponseDTO responseDTO = motorService.criar(requestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("id")
                .buildAndExpand(responseDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }

    @Operation(
            summary = "Lista os motores",
            description = "Lista todos os motores cadastrados"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Motores listados com sucesso"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @GetMapping
    public ResponseEntity<Page<MotorResponseDTO>> listarTodos(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){

        return ResponseEntity.ok().body(motorService.listarTodos(pageable));
    }

    @Operation(
            summary = "Busca um motor pelo seu identificador único",
            description = "Busca e retorna um motor pelo seu identificador único"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Motor encontrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Motor não encontrado com o ID informado"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @GetMapping("/{motorId}")
    public ResponseEntity<MotorResponseDTO> buscarPorId(
            @PathVariable
            @Parameter(description = "Identificador único do motor", example = "22")
            Integer motorId) {
        return ResponseEntity.ok().body(motorService.buscarPorId(motorId));
    }

    @Operation(
            summary = "Busca um motor pelo seu codigo ativo",
            description = "Busca e retorna um motor pelo seu codigo ativo"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Motor encontrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Motor não encontrado com o codigo ativo informado"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @GetMapping("/codigoAtivo")
    public ResponseEntity<MotorResponseDTO> buscarPorCodigoAtivo(@RequestParam String codigoAtivo) {
        return ResponseEntity.ok().body(motorService.buscarPorCodigoAtivo(codigoAtivo));
    }

    @Operation(
            summary = "Atualiza um motor",
            description = "Atualiza um motor buscando ele pelo seu identificador único informado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Motor atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Um ou mais campos da requisição são inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Motor não encontrado com o ID informado"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @PutMapping("/{motorId}")
    public ResponseEntity<MotorResponseDTO> atualizar(
            @PathVariable
            @Parameter(description = "Identificador unico do motor", example = "22")
            Integer motorId,
            @Valid @RequestBody MotorRequestDTO requestDTO){

        return ResponseEntity.ok().body(motorService.atualizar(motorId, requestDTO));
    }

    @Operation(
            summary = "Atualiza o status de um motor",
            description = "Atualiza o status de um motor buscando ele pelo seu identificador único informado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Motor atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Um ou mais campos da requisição são inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Motor não encontrado com o ID informado"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @PatchMapping("/{motorId}")
    public ResponseEntity<MotorResponseDTO> atualizarStatus(
            @PathVariable
            @Parameter(description = "Identificador unico do motor", example = "22")
            Integer motorId,
            @Valid @RequestBody MotorStatusUpdateDTO updateDTO){
        return ResponseEntity.ok().body(motorService.atualizarStatus(motorId, updateDTO));
    }
}

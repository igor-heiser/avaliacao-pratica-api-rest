package br.com.avaliacao.api_telemetria.controller;

import br.com.avaliacao.api_telemetria.dto.AlertaRequestDTO;
import br.com.avaliacao.api_telemetria.dto.AlertaResolucaoDTO;
import br.com.avaliacao.api_telemetria.dto.AlertaResponseDTO;
import br.com.avaliacao.api_telemetria.service.AlertaMotorService;
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
@RequestMapping("/api-telemetria/v1/alertas")
@Tag(
        name = "Alertas",
        description = "Endpoints referentes ao gerenciamento de alertas"
)
public class AlertaMotorController {

    private final AlertaMotorService alertaMotorService;

    public AlertaMotorController(AlertaMotorService alertaMotorService) {
        this.alertaMotorService = alertaMotorService;
    }

    @Operation(
            summary = "Cadastrar um alerta",
            description = "Cadastra um novo alerta e retorna o recurso com Location"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Alerta cadastrada com sucesso"
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
    public ResponseEntity<AlertaResponseDTO> criar(@Valid @RequestBody AlertaRequestDTO requestDTO) {
        AlertaResponseDTO responseDTO = alertaMotorService.criar(requestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("id")
                .buildAndExpand(responseDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }

    @Operation(
            summary = "Lista os alertas",
            description = "Lista todos os alertas cadastrados"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Alertas listados com sucesso"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @GetMapping
    public ResponseEntity<Page<AlertaResponseDTO>> listarTodos(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok().body(alertaMotorService.listarTodos(pageable));
    }

    @Operation(
            summary = "Busca um(os) alerta(s) pelo identificador único de um motor",
            description = "Busca e retorna um(os) alerta(s) pelo identificador único de um motor"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Alerta(s) encontrado(s) com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Alerta(s) não encontrado(s) com o ID do motor informado"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @GetMapping("/motores/{motorId}")
    public ResponseEntity<Page<AlertaResponseDTO>> buscarPorMotor(
            @PathVariable
            @Parameter(description = "Identificador único do motor", example = "22")
            Integer motorId,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok().body(alertaMotorService.buscarPorMotor(motorId, pageable));
    }

    @Operation(
            summary = "Atualiza o status de um alerta",
            description = "Atualiza o status de um alerta buscando ele pelo seu identificador único informado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Alerta atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Um ou mais campos da requisição são inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Alerta não encontrado com o ID informado"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @PatchMapping("/{alertaId}")
    public ResponseEntity<AlertaResponseDTO> atualizarStatusResolucao(
            @PathVariable
            @Parameter(description = "Identificador único do alerta", example = "22")
            Integer alertaId,
            @Valid @RequestBody AlertaResolucaoDTO resolucaoDTO) {

        return ResponseEntity.ok().body(alertaMotorService.atualizarStatusResolucao(alertaId, resolucaoDTO));
    }
}

package br.com.avaliacao.api_telemetria.controller;

import br.com.avaliacao.api_telemetria.dto.TelemetriaRequestDTO;
import br.com.avaliacao.api_telemetria.dto.TelemetriaResponseDTO;
import br.com.avaliacao.api_telemetria.service.TelemetriaService;
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
@RequestMapping("/api-telemetria/v1/telemetrias")
@Tag(
        name = "Telemetrias",
        description = "Endpoints referentes ao gerenciamento de telemetrias"
)
public class TelemetriaController {

    private final TelemetriaService telemetriaService;

    public TelemetriaController(TelemetriaService telemetriaService) {
        this.telemetriaService = telemetriaService;
    }

    @Operation(
            summary = "Lista as telemetrias",
            description = "Lista todas as telemetrias registradas"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Telemetrias listadas com sucesso"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @GetMapping
    public ResponseEntity<Page<TelemetriaResponseDTO>> listarTodos(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){

        return ResponseEntity.ok().body(telemetriaService.listarTodos(pageable));
    }

    @Operation(
            summary = "Busca uma telemetria pelo seu identificador único",
            description = "Busca e retorna uma telemetria pelo seu identificador único"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Telemetria encontrada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Telemetria não encontrada com o ID informado"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @GetMapping("/{telemetriaId}")
    public ResponseEntity<TelemetriaResponseDTO> buscarPorId(
            @PathVariable
            @Parameter(description = "Identificador único da telemetria", example = "22")
            Long telemetriaId){

        return ResponseEntity.ok().body(telemetriaService.buscarPorId(telemetriaId));
    }

    @Operation(
            summary = "Lista as telemetrias por motor",
            description = "Lista todas as telemetrias registradas pelo identificador único de um motor"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Telemetrias listadas com sucesso"
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
    @GetMapping("/motores/{motorId}")
    public ResponseEntity<Page<TelemetriaResponseDTO>> listarPorMotor(
            @PathVariable
            @Parameter(description = "Identificador unico do motor", example = "1")
            Integer motorId,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){

        return ResponseEntity.ok().body(telemetriaService.buscarPorMotor(motorId, pageable));
    }

    @Operation(
            summary = "Registra uma telemetria",
            description = "Registra uma nova telemetria e retorna o recurso com Location"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Telemetria registrada com sucesso"
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
    public ResponseEntity<TelemetriaResponseDTO> registrar(@Valid @RequestBody TelemetriaRequestDTO requestDTO){
        TelemetriaResponseDTO telemetriaResponseDTO = telemetriaService.registrar(requestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("id")
                .buildAndExpand(telemetriaResponseDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(telemetriaResponseDTO);
    }

}
package br.com.avaliacao.api_telemetria.controller;

import br.com.avaliacao.api_telemetria.dto.SetorResponseDTO;
import br.com.avaliacao.api_telemetria.dto.TelemetriaRequestDTO;
import br.com.avaliacao.api_telemetria.dto.TelemetriaResponseDTO;
import br.com.avaliacao.api_telemetria.service.TelemetriaService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * Controller REST responsável pelo gerenciamento do histórico de telemetrias.
 */
@RestController
@RequestMapping("/api-telemetria/v1/telemetrias")
@Tag(
        name = "Telemetrias",
        description = "Gerenciamento do historico de telemetrias"
)
public class TelemetriaController {

    private final TelemetriaService telemetriaService;

    public TelemetriaController(TelemetriaService telemetriaService) {
        this.telemetriaService = telemetriaService;
    }

    @GetMapping
    public ResponseEntity<Page<TelemetriaResponseDTO>> listarTodos(@PageableDefault Pageable pageable){
        Page<TelemetriaResponseDTO> telemetriaResponseDTOPageable = telemetriaService.listarTodos(pageable);

        return ResponseEntity.ok().body(telemetriaResponseDTOPageable);
    }

    @GetMapping("/{telemetriaId}")
    public ResponseEntity<TelemetriaResponseDTO> buscarPorId(
            @PathVariable
            @Parameter(
                    description = "Identificador único da telemetria",
                    example = "1"
            )
            Long telemetriaId
            ){

        return ResponseEntity.ok().body(telemetriaService.buscarPorId(telemetriaId));
    }

    @GetMapping("/{motorId}")
    public ResponseEntity<Page<TelemetriaResponseDTO>> listarPorMotor(
            @PathVariable
            @Parameter(
                    description = "Identificador unico do motor",
                    example = "1"
            )
            Integer motorId,
            @PageableDefault Pageable pageable
    ){

        Page<TelemetriaResponseDTO> telemetriaResponseDTOPageble = telemetriaService.buscarPorMotor(motorId, pageable);

        return ResponseEntity.ok().body(telemetriaResponseDTOPageble);
    }

    @PostMapping
    public ResponseEntity<TelemetriaResponseDTO> registrar(@Valid @RequestBody TelemetriaRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(telemetriaService.registrar(requestDTO));
    }

}
package br.com.avaliacao.api_telemetria.controller;

import br.com.avaliacao.api_telemetria.dto.AlertaRequestDTO;
import br.com.avaliacao.api_telemetria.dto.AlertaResolucaoDTO;
import br.com.avaliacao.api_telemetria.dto.AlertaResponseDTO;
import br.com.avaliacao.api_telemetria.service.AlertaMotorService;
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


public class AlertaMotorController {


    // Criar um novo alerta para um motor.
    public void criar() {

    }


    //Listar alertas com paginação.
    public void listarTodos() {

    }

    //Buscar histórico de alertas de um motor específico.
    public void buscarPorMotor() {

    }

    //Atualizar o status de resolução de um alerta (ex: marcar como resolvido/não resolvido).
    public void atualizarStatusResolucao() {

    }
}

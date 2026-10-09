package br.com.avaliacao.api_telemetria.repository;

import br.com.avaliacao.api_telemetria.entity.HistoricoTelemetria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoTelemetriaRepository extends JpaRepository<HistoricoTelemetria, Long> {
    Page<HistoricoTelemetria> findByMotorIdOrderByDataHoraDesc(Integer motorId, Pageable pageable);
}
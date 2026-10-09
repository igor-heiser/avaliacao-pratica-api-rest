package br.com.avaliacao.api_telemetria.repository;

import br.com.avaliacao.api_telemetria.entity.AlertaMotor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaMotorRepository extends JpaRepository<AlertaMotor, Integer> {
    Page<AlertaMotor> findByMotorIdOrderByDataAlertaDesc(Integer motorId, Pageable pageable);

    Page<AlertaMotor> findByResolvidoOrderByDataAlertaDesc(Boolean resolvido, Pageable pageable);
}

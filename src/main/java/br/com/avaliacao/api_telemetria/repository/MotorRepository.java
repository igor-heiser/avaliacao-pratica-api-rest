package br.com.avaliacao.api_telemetria.repository;

import br.com.avaliacao.api_telemetria.entity.Motor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotorRepository extends JpaRepository<Motor, Integer>{
    Optional<Motor> findByCodigoAtivo(String codigoAtivo);

    boolean existsByCodigoAtivo(String codigoAtivo);

    boolean existsByCodigoAtivoAndIdNot(String codigoAtivo, Integer id);
}

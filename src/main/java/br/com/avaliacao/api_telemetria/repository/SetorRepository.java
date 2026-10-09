package br.com.avaliacao.api_telemetria.repository;

import br.com.avaliacao.api_telemetria.entity.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer> {
    boolean existsByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCaseAndIdNot(String nome, Integer id);
}

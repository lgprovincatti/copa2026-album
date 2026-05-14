package br.com.copa2026.backend.repository;

import br.com.copa2026.backend.entity.Figurinha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FigurinhaRepository
        extends JpaRepository<Figurinha, Long> {

    List<Figurinha> findBySelecaoSiglaOrderByNumero(String sigla);
}
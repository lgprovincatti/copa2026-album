package br.com.copa2026.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.copa2026.backend.entity.Selecao;

public interface SelecaoRepository
        extends JpaRepository<Selecao, Long> {
}
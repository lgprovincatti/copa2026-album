package br.com.copa2026.backend.repository;

import br.com.copa2026.backend.entity.AlbumNovo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumNovoRepository
        extends JpaRepository<AlbumNovo, Long> {
}
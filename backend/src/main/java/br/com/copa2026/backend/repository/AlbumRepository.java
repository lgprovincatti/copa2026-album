package br.com.copa2026.backend.repository;

import br.com.copa2026.backend.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository
        extends JpaRepository<Album, Long> {

    @Query("""
            SELECT COUNT(a)
            FROM Album a
            """)
    Long total();

    @Query("""
            SELECT COUNT(a)
            FROM Album a
            WHERE a.possui = 'S'
            """)
    Long possui();

    @Query("""
            SELECT COUNT(a)
            FROM Album a
            WHERE a.possui = 'N'
            """)
    Long faltam();

    Optional<Album> findByFigurinhaId(Long figurinhaId);

    List<Album> findByFigurinhaSelecaoSiglaOrderByFigurinhaNumero(
            String sigla
    );
}
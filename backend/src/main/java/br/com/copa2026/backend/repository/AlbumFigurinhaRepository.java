package br.com.copa2026.backend.repository;

import br.com.copa2026.backend.entity.AlbumFigurinha;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlbumFigurinhaRepository
        extends JpaRepository<AlbumFigurinha, Long> {

    Optional<AlbumFigurinha>
    findByAlbumIdAndFigurinhaId(
            Long albumId,
            Long figurinhaId
    );

    List<AlbumFigurinha>
    findByAlbumIdAndFigurinhaSelecaoSiglaOrderByFigurinhaNumero(
            Long albumId,
            String sigla
    );

    Long countByAlbumId(
            Long albumId
    );

    Long countByAlbumIdAndPossui(
            Long albumId,
            String possui
    );
}
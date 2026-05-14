package br.com.copa2026.backend.service;

import br.com.copa2026.backend.dto.DashboardDTO;
import br.com.copa2026.backend.dto.FaltantesDTO;
import br.com.copa2026.backend.dto.FigurinhaDTO;
import br.com.copa2026.backend.dto.SelecaoDashboardDTO;

import br.com.copa2026.backend.entity.AlbumFigurinha;

import br.com.copa2026.backend.repository.AlbumFigurinhaRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final AlbumFigurinhaRepository repository;

    public DashboardService(
            AlbumFigurinhaRepository repository
    ) {
        this.repository = repository;
    }

    public DashboardDTO obterDashboard(
            Long albumId
    ) {

        Long total =
                repository.countByAlbumId(
                        albumId
                );

        Long possui =
                repository.countByAlbumIdAndPossui(
                        albumId,
                        "S"
                );

        Long faltam =
                total - possui;

        Double percentual = 0.0;

        if (total > 0) {

            percentual =
                    (possui.doubleValue()
                            / total.doubleValue()) * 100.0;
        }

        return new DashboardDTO(
                total,
                possui,
                faltam,
                percentual
        );
    }

    public void marcarComoPossui(
            Long albumId,
            Long figurinhaId
    ) {

        AlbumFigurinha album =
                repository
                        .findByAlbumIdAndFigurinhaId(
                                albumId,
                                figurinhaId
                        )
                        .orElseThrow();

        if ("S".equals(
                album.getPossui()
        )) {

            album.setPossui("N");

        } else {

            album.setPossui("S");
        }

        album.setDataAtualizacao(
                LocalDateTime.now()
        );

        repository.save(album);
    }

    public SelecaoDashboardDTO obterSelecao(
            Long albumId,
            String sigla
    ) {

        List<AlbumFigurinha> albuns =
                repository
                        .findByAlbumIdAndFigurinhaSelecaoSiglaOrderByFigurinhaNumero(
                                albumId,
                                sigla
                        );

        SelecaoDashboardDTO dto =
                new SelecaoDashboardDTO();

        if (albuns.isEmpty()) {

            return dto;
        }

        dto.setNome(
                albuns.get(0)
                        .getFigurinha()
                        .getSelecao()
                        .getNome()
        );

        dto.setSigla(sigla);

        Long total =
                (long) albuns.size();

        Long possui =
                albuns.stream()
                        .filter(a ->
                                "S".equals(
                                        a.getPossui()
                                )
                        )
                        .count();

        Long faltam =
                total - possui;

        Double percentual = 0.0;

        if (total > 0) {

            percentual =
                    (possui.doubleValue()
                            / total.doubleValue()) * 100.0;
        }

        dto.setTotal(total);

        dto.setPossui(possui);

        dto.setFaltam(faltam);

        dto.setPercentual(percentual);

        dto.setFigurinhas(

                albuns.stream()
                        .map(a ->
                                new FigurinhaDTO(
                                        a.getFigurinha().getId(),
                                        a.getFigurinha().getCodigo(),
                                        a.getPossui()
                                )
                        )
                        .toList()
        );

        return dto;
    }

    public List<FaltantesDTO> obterFaltantes(
            Long albumId
    ) {

        List<AlbumFigurinha> albuns =
                repository.findAll();

        Map<String, List<AlbumFigurinha>> agrupado =

                albuns.stream()

                        .filter(a ->
                                a.getAlbum()
                                        .getId()
                                        .equals(albumId)
                        )

                        .filter(a ->
                                "N".equals(
                                        a.getPossui()
                                )
                        )

                        .collect(
                                Collectors.groupingBy(
                                        a ->
                                                a.getFigurinha()
                                                        .getSelecao()
                                                        .getSigla()
                                )
                        );

        return agrupado.entrySet()
                .stream()
                .map(entry -> {

                    FaltantesDTO dto =
                            new FaltantesDTO();

                    dto.setSigla(
                            entry.getKey()
                    );

                    dto.setFaltantes(

                            entry.getValue()
                                    .stream()
                                    .map(a ->
                                            a.getFigurinha()
                                                    .getCodigo()
                                    )
                                    .toList()
                    );

                    return dto;
                })
                .toList();
    }
}
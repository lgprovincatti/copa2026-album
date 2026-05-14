package br.com.copa2026.backend.service;

import br.com.copa2026.backend.dto.AlbumDTO;
import br.com.copa2026.backend.dto.CreateAlbumDTO;

import br.com.copa2026.backend.entity.AlbumFigurinha;
import br.com.copa2026.backend.entity.AlbumNovo;
import br.com.copa2026.backend.entity.Figurinha;

import br.com.copa2026.backend.repository.AlbumFigurinhaRepository;
import br.com.copa2026.backend.repository.AlbumNovoRepository;
import br.com.copa2026.backend.repository.FigurinhaRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumNovoRepository repository;

    private final FigurinhaRepository figurinhaRepository;

    private final AlbumFigurinhaRepository albumFigurinhaRepository;

    public AlbumService(
            AlbumNovoRepository repository,
            FigurinhaRepository figurinhaRepository,
            AlbumFigurinhaRepository albumFigurinhaRepository
    ) {
        this.repository = repository;
        this.figurinhaRepository = figurinhaRepository;
        this.albumFigurinhaRepository =
                albumFigurinhaRepository;
    }

    public List<AlbumDTO> listar() {

        return repository.findAll()
                .stream()
                .map(a ->
                        new AlbumDTO(
                                a.getId(),
                                a.getNome()
                        )
                )
                .toList();
    }

    public void criar(
            CreateAlbumDTO dto
    ) {

        AlbumNovo album =
                new AlbumNovo();

        album.setNome(
                dto.getNome()
        );

        album.setAtivo("S");

        album.setDataCriacao(
                LocalDateTime.now()
        );

        repository.save(album);

        List<Figurinha> figurinhas =
                figurinhaRepository.findAll();

        for (Figurinha figurinha
                : figurinhas) {

            AlbumFigurinha af =
                    new AlbumFigurinha();

            af.setAlbum(album);

            af.setFigurinha(figurinha);

            af.setPossui("N");

            albumFigurinhaRepository
                    .save(af);
        }
    }

    public void excluir(
            Long albumId
    ) {

        albumFigurinhaRepository
                .deleteAll(

                        albumFigurinhaRepository
                                .findAll()
                                .stream()
                                .filter(a ->
                                        a.getAlbum()
                                                .getId()
                                                .equals(albumId)
                                )
                                .toList()
                );

        repository.deleteById(
                albumId
        );
    }
}
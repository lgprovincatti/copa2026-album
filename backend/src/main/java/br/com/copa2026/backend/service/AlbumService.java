package br.com.copa2026.backend.service;

import br.com.copa2026.backend.dto.AlbumDTO;

import br.com.copa2026.backend.entity.AlbumNovo;

import br.com.copa2026.backend.repository.AlbumNovoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumNovoRepository repository;

    public AlbumService(
            AlbumNovoRepository repository
    ) {
        this.repository = repository;
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
}
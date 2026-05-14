package br.com.copa2026.backend.controller;

import br.com.copa2026.backend.dto.AlbumDTO;

import br.com.copa2026.backend.service.AlbumService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlbumController {

    private final AlbumService service;

    public AlbumController(
            AlbumService service
    ) {
        this.service = service;
    }

    @GetMapping("/albuns")
    public List<AlbumDTO> listar() {

        return service.listar();
    }
}
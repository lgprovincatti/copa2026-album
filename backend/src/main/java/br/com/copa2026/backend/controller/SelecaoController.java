package br.com.copa2026.backend.controller;

import br.com.copa2026.backend.entity.Selecao;
import br.com.copa2026.backend.repository.SelecaoRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SelecaoController {

    private final SelecaoRepository repository;

    public SelecaoController(SelecaoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/selecoes")
    public List<Selecao> listar() {
        return repository.findAll();
    }
}
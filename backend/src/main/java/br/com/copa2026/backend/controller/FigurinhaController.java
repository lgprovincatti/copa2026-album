package br.com.copa2026.backend.controller;

import br.com.copa2026.backend.entity.Figurinha;
import br.com.copa2026.backend.repository.FigurinhaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/figurinhas")
public class FigurinhaController {

    private final FigurinhaRepository repository;

    public FigurinhaController(FigurinhaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{sigla}")
    public List<Figurinha> listarPorSelecao(
            @PathVariable String sigla
    ) {
        return repository
                .findBySelecaoSiglaOrderByNumero(sigla);
    }
}
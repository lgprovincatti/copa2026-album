package br.com.copa2026.backend.controller;

import br.com.copa2026.backend.dto.DashboardDTO;
import br.com.copa2026.backend.dto.FaltantesDTO;
import br.com.copa2026.backend.dto.SelecaoDashboardDTO;

import br.com.copa2026.backend.service.DashboardService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DashboardController {

    private final DashboardService service;

    public DashboardController(
            DashboardService service
    ) {
        this.service = service;
    }

    @GetMapping("/dashboard")
    public DashboardDTO dashboard(
            @RequestParam Long albumId
    ) {

        return service.obterDashboard(
                albumId
        );
    }

    @GetMapping("/selecao/{sigla}")
    public SelecaoDashboardDTO selecao(
            @PathVariable String sigla,
            @RequestParam Long albumId
    ) {

        return service.obterSelecao(
                albumId,
                sigla
        );
    }

    @GetMapping("/faltantes")
    public List<FaltantesDTO> faltantes(
            @RequestParam Long albumId
    ) {

        return service.obterFaltantes(
                albumId
        );
    }

    @PutMapping("/album/{figurinhaId}")
    public void marcarComoPossui(
            @PathVariable Long figurinhaId,
            @RequestParam Long albumId
    ) {

        service.marcarComoPossui(
                albumId,
                figurinhaId
        );
    }
}
package br.com.copa2026.backend.controller;

import br.com.copa2026.backend.dto.DashboardDTO;
import br.com.copa2026.backend.dto.FaltantesDTO;
import br.com.copa2026.backend.dto.SelecaoDashboardDTO;

import br.com.copa2026.backend.service.DashboardService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
    public DashboardDTO dashboard() {

        return service.obterDashboard();
    }

    @GetMapping("/selecao/{sigla}")
    public SelecaoDashboardDTO selecao(
            @PathVariable String sigla
    ) {

        return service.obterSelecao(sigla);
    }

    @GetMapping("/faltantes")
    public List<FaltantesDTO> faltantes() {

        return service.obterFaltantes();
    }

    @PutMapping("/album/{figurinhaId}")
    public void marcarComoPossui(
            @PathVariable Long figurinhaId
    ) {

        service.marcarComoPossui(
                figurinhaId
        );
    }
}
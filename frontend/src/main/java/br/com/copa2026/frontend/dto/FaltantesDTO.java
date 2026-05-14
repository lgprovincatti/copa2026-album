package br.com.copa2026.frontend.dto;

import java.util.List;

public class FaltantesDTO {

    private String sigla;

    private List<String> faltantes;

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<String> getFaltantes() {
        return faltantes;
    }

    public void setFaltantes(
            List<String> faltantes
    ) {
        this.faltantes = faltantes;
    }
}
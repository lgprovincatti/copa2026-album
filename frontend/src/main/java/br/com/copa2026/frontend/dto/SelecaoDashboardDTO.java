package br.com.copa2026.frontend.dto;

import java.util.List;

public class SelecaoDashboardDTO {

    private String nome;

    private String sigla;

    private Long total;

    private Long possui;

    private Long faltam;

    private Double percentual;

    private List<FigurinhaDTO> figurinhas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPossui() {
        return possui;
    }

    public void setPossui(Long possui) {
        this.possui = possui;
    }

    public Long getFaltam() {
        return faltam;
    }

    public void setFaltam(Long faltam) {
        this.faltam = faltam;
    }

    public Double getPercentual() {
        return percentual;
    }

    public void setPercentual(Double percentual) {
        this.percentual = percentual;
    }

    public List<FigurinhaDTO> getFigurinhas() {
        return figurinhas;
    }

    public void setFigurinhas(
            List<FigurinhaDTO> figurinhas
    ) {
        this.figurinhas = figurinhas;
    }
}
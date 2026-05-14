package br.com.copa2026.frontend.dto;

public class DashboardDTO {

    private Long total;

    private Long possui;

    private Long faltam;

    private Double percentual;

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
}
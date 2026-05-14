package br.com.copa2026.frontend.dto;

public class SelecaoDTO {

    private Long id;

    private String nome;

    private String sigla;

    private String grupoCopa;

    private String ativa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getGrupoCopa() {
        return grupoCopa;
    }

    public void setGrupoCopa(String grupoCopa) {
        this.grupoCopa = grupoCopa;
    }

    public String getAtiva() {
        return ativa;
    }

    public void setAtiva(String ativa) {
        this.ativa = ativa;
    }
}
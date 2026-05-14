package br.com.copa2026.frontend.dto;

public class FigurinhaDTO {

    private Long id;

    private String codigo;

    private String possui;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPossui() {
        return possui;
    }

    public void setPossui(String possui) {
        this.possui = possui;
    }
}
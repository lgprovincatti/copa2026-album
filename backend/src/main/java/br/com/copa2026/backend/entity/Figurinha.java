package br.com.copa2026.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Figurinha {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String codigo;

    private Integer numero;

    private String tipo;

    @ManyToOne
    @JoinColumn(name = "selecao_id")
    private Selecao selecao;

    public Long getId() {
        return id;
    }

    public void setId(
            Long id
    ) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(
            String codigo
    ) {
        this.codigo = codigo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(
            Integer numero
    ) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(
            String tipo
    ) {
        this.tipo = tipo;
    }

    public Selecao getSelecao() {
        return selecao;
    }

    public void setSelecao(
            Selecao selecao
    ) {
        this.selecao = selecao;
    }
}
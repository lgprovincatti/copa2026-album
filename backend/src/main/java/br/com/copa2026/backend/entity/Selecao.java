package br.com.copa2026.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "selecao")
@Getter
@Setter
public class Selecao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sigla;

    @Column(name = "grupo_copa")
    private String grupoCopa;

    private String ativa;
}
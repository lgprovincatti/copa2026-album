package br.com.copa2026.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "figurinha")
@Getter
@Setter
public class Figurinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private Integer numero;

    private String descricao;

    @Column(name = "categoria_id")
    private Long categoriaId;

    @ManyToOne
    @JoinColumn(name = "selecao_id")
    private Selecao selecao;

    private String rara;

    private String observacao;
}
package br.com.copa2026.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "album")
@Getter
@Setter
public class Album {

    @Id
    @Column(name = "figurinha_id")
    private Long figurinhaId;

    private String possui;

    private Integer repetidas;

    @Column(name = "data_atualizacao")
    private java.time.LocalDateTime dataAtualizacao;

    @OneToOne
    @JoinColumn(name = "figurinha_id", insertable = false, updatable = false)
    private Figurinha figurinha;
}
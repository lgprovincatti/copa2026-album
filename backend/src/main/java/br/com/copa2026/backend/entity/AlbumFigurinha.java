package br.com.copa2026.backend.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ALBUM_FIGURINHA")
@Getter
@Setter
public class AlbumFigurinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private AlbumNovo album;

    @ManyToOne
    @JoinColumn(name = "figurinha_id")
    private Figurinha figurinha;

    private String possui;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
}
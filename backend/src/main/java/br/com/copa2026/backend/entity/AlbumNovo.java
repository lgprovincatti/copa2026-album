package br.com.copa2026.backend.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ALBUM_NOVO")
@Getter
@Setter
public class AlbumNovo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    private String ativo;
}
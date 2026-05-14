package br.com.copa2026.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SelecaoDashboardDTO {

    private String nome;

    private String sigla;

    private Long total;

    private Long possui;

    private Long faltam;

    private Double percentual;

    private List<FigurinhaDTO> figurinhas;
}
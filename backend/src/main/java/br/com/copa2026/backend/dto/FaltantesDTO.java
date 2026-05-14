package br.com.copa2026.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FaltantesDTO {

    private String sigla;

    private List<String> faltantes;
}
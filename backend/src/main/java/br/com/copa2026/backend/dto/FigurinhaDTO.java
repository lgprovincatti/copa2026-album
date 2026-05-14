package br.com.copa2026.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FigurinhaDTO {

    private Long id;

    private String codigo;

    private String possui;
}
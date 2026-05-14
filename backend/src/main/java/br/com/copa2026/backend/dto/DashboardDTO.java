package br.com.copa2026.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DashboardDTO {

    private Long total;

    private Long possui;

    private Long faltam;

    private Double percentual;
}
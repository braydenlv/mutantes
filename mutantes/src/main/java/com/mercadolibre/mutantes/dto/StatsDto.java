package com.mercadolibre.mutantes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//For request and response
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatsDto {
    private Long countMutantDNA;
    private Long countHumanDNA;
    private String ratio;
}

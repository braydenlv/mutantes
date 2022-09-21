package com.mercadolibre.mutantes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Respesent the entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsEntity {
    private Long count_mutant_dna;
    private Long count_human_dna;
    private String ratio;
}

package com.mercadolibre.mutantes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//For request and response
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MutantDto {

    private String id;
    private List<String> dna;
    private Boolean isMutant;

}

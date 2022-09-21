package com.mercadolibre.mutantes.utils;

import com.mercadolibre.mutantes.dto.MutantDto;

public class MutantsClassifier {

    /**
     * Get an Objet Data Transfer Objet (DTO) for looking for mutants.
     *
     * @param mutantDto
     * @return True if in the nitrogen base math with the rules for mutants
     */
    public Boolean isMutant(MutantDto mutantDto) {

        InspectArray inspectDNAArray = new InspectArray();
        ConvertListToArray convertListToArray = new ConvertListToArray();

        char[][] dna = convertListToArray.convertList(mutantDto.getDna());

        return inspectDNAArray.inAllDirections(dna);
    }
}



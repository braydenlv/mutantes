package com.mercadolibre.mutantes.utils;

import com.mercadolibre.mutantes.dto.MutantDto;

public class CheckSequenceDNA {

    private char[][] dna;

    /**
     * <p>Get a MutanteModel and check the nitrogen base structure for assuring the next rules:
     * <p>1. the minimum size of the matrix is 4 x 4 (MutantsProperties.SIZE_SECUENCE).
     * <p>2. The rows and culumns number must be the same (N x N).
     * <p>3. The DNA only must have the letters A, C, G and T in uppercase.
     *
     * @param mutantDto
     * @return true if the information is ok.
     */
    public boolean checkStructureDNA(MutantDto mutantDto) {

        ConvertListToArray convertListToArray = new ConvertListToArray();
        this.dna = convertListToArray.convertList(mutantDto.getDna());

        if (dna == null) {
            return false;
        }

        return checkMinimunInformationDNA() && checkInformationDNA();
    }

    //Check the minimun size of the secuence
    private Boolean checkMinimunInformationDNA() {
        return MutantsProperties.MINIMUM_SEQUENCE_LENGTH <= dna.length;
    }

    //Check that the nitrogen base only has A, T, G and C characters
    private Boolean checkInformationDNA() {
        for (int row = 0; row < dna.length; row++) {
            for (int column = 0; column < dna.length; column++) {
                char c = dna[row][column];
                if (c != 'A' && c != 'T' && c != 'G' && c != 'C') {
                    return false;
                }
            }
        }
        return true;
    }
}

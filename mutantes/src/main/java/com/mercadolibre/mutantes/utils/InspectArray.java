package com.mercadolibre.mutantes.utils;

public class InspectArray {

    private int MinimumSequenceLength = MutantsProperties.MINIMUM_SEQUENCE_LENGTH;
    private int defaultMinimumNumberofSequences = MutantsProperties.MINIMUM_NUMBER_OF_SEQUENCES;
    private int currentSequenceLength;
    private int currentNumberOfSequences;

    /**
     * execute the methods horizontally, vertically, diagonallySinceRigth  and diagonallySinceLeft of this class
     *
     * @param dna
     * @return true if the inspection sum exceeds the default sequences count
     */
    public Boolean inAllDirections(char[][] dna) {

        int finalCountOfSequences = horizontally(dna) + vertically(dna) + diagonallySinceRight(dna) + diagonallySinceLeft(dna);

        return defaultMinimumNumberofSequences <= finalCountOfSequences;
    }

    /**
     * <pre>
     * count the number oh sequences found horizontally
     * [x][x][x][x]
     * [-][-][-][-]
     * [-][-][-][-]
     * [-][-][-][-]
     * </pre>
     *
     * @param dna
     * @return The number of sequences found (int)
     */
    public int horizontally(char[][] dna) {
        int length = dna.length;
        currentNumberOfSequences = 0;

        for (int row = 0; row < length; row++) {
            //clean the variables every time row changes
            char lastCharacter = ' ';
            char currentCharacter = ' ';
            currentSequenceLength = 1;

            for (int column = 0; column < length; column++) {
                currentCharacter = dna[row][column];

                if (lastCharacter == currentCharacter) {
                    currentSequenceLength++;
                } else if (MinimumSequenceLength <= currentSequenceLength) {
                    currentNumberOfSequences++;
                    currentSequenceLength = 1;
                } else {
                    currentSequenceLength = 1;
                }
                lastCharacter = currentCharacter;
            }

            if (MinimumSequenceLength <= currentSequenceLength) {
                currentNumberOfSequences++;
            }
        }
        return currentNumberOfSequences;
    }

    /**
     * <pre>
     * count the number oh sequences found vertically
     * [x][-][-][-]
     * [x][-][-][-]
     * [x][-][-][-]
     * [x][-][-][-]
     * </pre>
     *
     * @param dna
     * @return The number of sequences found (int)
     */
    public int vertically(char[][] dna) {
        int length = dna.length;
        currentNumberOfSequences = 0;

        for (int column = 0; column < length; column++) {
            //clean the variables every time row changes
            char lastCharacter = ' ';
            char currentCharacter = ' ';
            currentSequenceLength = 1;

            for (int row = 0; row < length; row++) {
                currentCharacter = dna[row][column];

                if (lastCharacter == currentCharacter) {
                    currentSequenceLength++;
                } else if (MinimumSequenceLength <= currentSequenceLength) {
                    currentNumberOfSequences++;
                    currentSequenceLength = 1;
                } else {
                    currentSequenceLength = 1;
                }
                lastCharacter = currentCharacter;
            }

            if (MinimumSequenceLength <= currentSequenceLength) {
                currentNumberOfSequences++;
            }
        }
        return currentNumberOfSequences;
    }

    /**
     * <pre>
     * count the number oh sequences found diagonally since right side
     * start here -->[x][-][-][-]
     *               [-][x][-][-]
     *               [-][-][x][-]
     *               [-][-][-][x]
     * </pre>
     *
     * @param dna
     * @return The number of sequences found (int)
     */
    public int diagonallySinceRight(char[][] dna) {
        int length = dna.length;
        currentNumberOfSequences = 0;

        /*********************************************************
         * Extra variables to correctly iterate through the array *
         *********************************************************/
        int numberOfDiagonals = ((length - MinimumSequenceLength) * 2) + 1; //Number of diagonals with possible outcomes
        int inicialNum = MinimumSequenceLength - length; //Ever is the same number, only change when you modify the minimum sequence length property

        for (int diagonal = 0; diagonal < numberOfDiagonals; diagonal++) {
            //clean the variables every time row changes
            char lastCharacter = ' ';
            char currentCharacter = ' ';
            currentSequenceLength = 1;

            /*********************************************************
             * Extra variables to correctly iterate through the array *
             *********************************************************/
            int finalNum = inicialNum + diagonal; //It's an auxiliar number for calculations
            int row = Math.min(0, finalNum) * (-1); //return the inicial row
            int column = Math.max(0, finalNum); //return the inicial column
            int numberOfElements = length - row - column;//return the number of  elements in the diagonal

            for (int element = 0; element < numberOfElements; element++) {
                currentCharacter = dna[row][column];

                if (lastCharacter == currentCharacter) {
                    currentSequenceLength++;
                } else if (MinimumSequenceLength <= currentSequenceLength) {
                    currentNumberOfSequences++;
                    currentSequenceLength = 1;
                } else {
                    currentSequenceLength = 1;
                }
                lastCharacter = currentCharacter;
                row++;
                column++;
            }

            if (MinimumSequenceLength <= currentSequenceLength) {
                currentNumberOfSequences++;
            }
        }
        return currentNumberOfSequences;
    }

    /**
     * <pre>
     * count the number oh sequences found diagonally since left side
     * [-][-][-][x] <-- start here
     * [-][-][x][-]
     * [-][x][-][-]
     * [x][-][-][-]
     * </pre>
     *
     * @param dna
     * @return The number of sequences found (int)
     */
    public int diagonallySinceLeft(char[][] dna) {
        int length = dna.length;
        currentNumberOfSequences = 0;

        /*********************************************************
         * Extra variables to correctly iterate through the array *
         *********************************************************/
        int numberOfDiagonals = ((length - MinimumSequenceLength) * 2) + 1; //Number of diagonals with possible outcomes
        int inicialNum = MinimumSequenceLength - 1; //No matter the size of the matrix, it always starts at the same position

        for (int diagonal = 0; diagonal < numberOfDiagonals; diagonal++) {
            char lastCharacter = ' ';
            char currentCharacter = ' ';
            currentSequenceLength = 1;

            /*********************************************************
             * Extra variables to correctly iterate through the array *
             *********************************************************/
            int finalNum = inicialNum + diagonal; //It's an auxiliar number for calculations
            int row = Math.max(0, finalNum - (length - 1)); //return the inicial row for the diagonal
            int column = Math.min(length - 1, finalNum); //return the inicial column for the diagonal
            int numberOfElements = (MinimumSequenceLength + diagonal) - (row * 2); //return the number of  elements in the diagonal

            for (int element = 0; element < numberOfElements; element++) {
                currentCharacter = dna[row][column];

                if (lastCharacter == currentCharacter) {
                    currentSequenceLength++;
                } else if (MinimumSequenceLength <= currentSequenceLength) {
                    currentNumberOfSequences++;
                    currentSequenceLength = 1;
                } else {
                    currentSequenceLength = 1;
                }
                lastCharacter = currentCharacter;
                row++;
                column--;
            }

            if (MinimumSequenceLength <= currentSequenceLength) {
                currentNumberOfSequences++;
            }
        }
        return currentNumberOfSequences;
    }
}

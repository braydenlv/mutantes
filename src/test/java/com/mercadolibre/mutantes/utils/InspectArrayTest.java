package com.mercadolibre.mutantes.utils;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InspectArrayTest {

    private InspectArray inspectArray = new InspectArray();
    private char[][] arrayMutant = new char[5][5];
    private char[][] arrayHuman = new char[4][4];


    @BeforeEach
    void setUp() {
        arrayMutant[0][0] = 'G';
        arrayMutant[0][1] = 'A';
        arrayMutant[0][2] = 'A';
        arrayMutant[0][3] = 'A';
        arrayMutant[0][4] = 'A';
        arrayMutant[1][0] = 'T';
        arrayMutant[1][1] = 'G';
        arrayMutant[1][2] = 'T';
        arrayMutant[1][3] = 'G';
        arrayMutant[1][4] = 'C';
        arrayMutant[2][0] = 'T';
        arrayMutant[2][1] = 'T';
        arrayMutant[2][2] = 'G';
        arrayMutant[2][3] = 'T';
        arrayMutant[2][4] = 'C';
        arrayMutant[3][0] = 'T';
        arrayMutant[3][1] = 'G';
        arrayMutant[3][2] = 'T';
        arrayMutant[3][3] = 'G';
        arrayMutant[3][4] = 'C';
        arrayMutant[4][0] = 'G';
        arrayMutant[4][1] = 'T';
        arrayMutant[4][2] = 'T';
        arrayMutant[4][3] = 'T';
        arrayMutant[4][4] = 'C';

        arrayHuman[0][0] = 'A';
        arrayHuman[0][1] = 'C';
        arrayHuman[0][2] = 'T';
        arrayHuman[0][3] = 'G';
        arrayHuman[1][0] = 'G';
        arrayHuman[1][1] = 'T';
        arrayHuman[1][2] = 'C';
        arrayHuman[1][3] = 'A';
        arrayHuman[2][0] = 'A';
        arrayHuman[2][1] = 'C';
        arrayHuman[2][2] = 'T';
        arrayHuman[2][3] = 'G';
        arrayHuman[3][0] = 'G';
        arrayHuman[3][1] = 'T';
        arrayHuman[3][2] = 'C';
        arrayHuman[3][3] = 'A';
    }

    @Test
    void testDiagonallySinceLeftMutant() {
        assertEquals(1, inspectArray.diagonallySinceLeft(arrayMutant));
    }

    @Test
    void testDiagonallySinceRightMutant() {
        assertEquals(2, inspectArray.diagonallySinceRight(arrayMutant));
    }

    @Test
    void testHorizontallyMutant() {
        assertEquals(1, inspectArray.horizontally(arrayMutant));
    }

    @Test
    void testInAllDirectionsMutant() {
        assertEquals(true, inspectArray.inAllDirections(arrayMutant));
    }

    @Test
    void testVerticallyMutant() {
        assertEquals(1, inspectArray.vertically(arrayMutant));
    }

    @Test
    void testDiagonallySinceLeftHuman() {
        assertEquals(0, inspectArray.diagonallySinceLeft(arrayHuman));
    }

    @Test
    void testDiagonallySinceRightHuman() {
        assertEquals(0, inspectArray.diagonallySinceRight(arrayHuman));
    }

    @Test
    void testHorizontallyHuman() {
        assertEquals(0, inspectArray.horizontally(arrayHuman));
    }

    @Test
    void testInAllDirectionsHuman() {
        assertEquals(false, inspectArray.inAllDirections(arrayHuman));
    }

    @Test
    void testVerticallyHuman() {
        assertEquals(0, inspectArray.vertically(arrayHuman));
    }
}

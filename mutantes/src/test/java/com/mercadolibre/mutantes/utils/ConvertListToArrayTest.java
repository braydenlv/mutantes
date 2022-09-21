package com.mercadolibre.mutantes.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class ConvertListToArrayTest {

    ConvertListToArray convertListToArray = new ConvertListToArray();
    private List<String> goodList;
    private List<String> nxmList;
    private List<String> mxnList;
    private char[][] goodArray = new char[4][4];

    @BeforeEach
    void setUp() {
        goodList = new ArrayList<>();
        goodList.add("CGGC");
        goodList.add("CAGT");
        goodList.add("TTAT");
        goodList.add("AGAA");

        nxmList = new ArrayList<>();
        nxmList.add("CGGCC");
        nxmList.add("CAGTC");
        nxmList.add("TTATC");
        nxmList.add("AGAAC");

        mxnList = new ArrayList<>();
        mxnList.add("CGGC");
        mxnList.add("CAGT");
        mxnList.add("TTAT");
        mxnList.add("AGAA");
        mxnList.add("AGAA");

        goodArray[0][0] = 'C';
        goodArray[0][1] = 'G';
        goodArray[0][2] = 'G';
        goodArray[0][3] = 'C';
        goodArray[1][0] = 'C';
        goodArray[1][1] = 'A';
        goodArray[1][2] = 'G';
        goodArray[1][3] = 'T';
        goodArray[2][0] = 'T';
        goodArray[2][1] = 'T';
        goodArray[2][2] = 'A';
        goodArray[2][3] = 'T';
        goodArray[3][0] = 'A';
        goodArray[3][1] = 'G';
        goodArray[3][2] = 'A';
        goodArray[3][3] = 'A';

    }

    @Test
    void testConvertListNxN() {
        assertArrayEquals(goodArray, convertListToArray.convertList(goodList));
    }

    @Test
    void testConvertListNxM() {
        assertNull(convertListToArray.convertList(nxmList));
    }

    @Test
    void testConvertListMxN() {
        assertNull(convertListToArray.convertList(mxnList));
    }
}

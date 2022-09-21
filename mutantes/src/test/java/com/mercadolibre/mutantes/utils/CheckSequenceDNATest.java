package com.mercadolibre.mutantes.utils;

import com.mercadolibre.mutantes.dto.MutantDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CheckSequenceDNATest {

    private CheckSequenceDNA checkSequenceDNA = new CheckSequenceDNA();
    private MutantDto goodMutantEntity;
    private MutantDto mutantDnaWithLowercase;
    private MutantDto mutantDnaWithoutOneChar;
    private MutantDto mutantDnaWithoutOneRow;
    private MutantDto mutantDnaWithDifferentChar;
    private MutantDto mutantDnaShort;
    private MutantDto mutantDnaWithoutOneColumn;

    @BeforeEach
    void setUp() {
        List<String> dna1 = new ArrayList<>();
        dna1.clear();
        dna1.add("ATGCGA");
        dna1.add("CAGTGC");
        dna1.add("TTATGT");
        dna1.add("AGAAGG");
        dna1.add("CCCCTA");
        dna1.add("TCACTG");
        goodMutantEntity = new MutantDto("1", dna1, true);

        List<String> dna5 = new ArrayList<>();
        dna5.add("aGGCGA");
        dna5.add("CAGTCC");
        dna5.add("TTATGT");
        dna5.add("AGAAGG");
        dna5.add("CCTCTA");
        dna5.add("TCACTG");
        mutantDnaWithLowercase = new MutantDto("2", dna5, false);

        List<String> dna6 = new ArrayList<>();
        dna6.add("GGGCGA");
        dna6.add("CAGTCC");
        dna6.add("TTATGT");
        dna6.add("AGAAG");
        dna6.add("CCTCTA");
        dna6.add("TCACTA");
        mutantDnaWithoutOneChar = new MutantDto("3", dna6, false);

        List<String> dna7 = new ArrayList<>();
        dna7.add("TGGCGA");
        dna7.add("CAGTCC");
        dna7.add("TTATGT");
        dna7.add("AGAAGG");
        dna7.add("CCTCTG");
        mutantDnaWithoutOneRow = new MutantDto("4", dna7, false);

        List<String> dna8 = new ArrayList<>();
        dna8.add("CGGCGA");
        dna8.add("CAGTCC");
        dna8.add("TTATGT");
        dna8.add("AGAAGG");
        dna8.add("CCTCTG");
        dna8.add("CGGCGE");
        mutantDnaWithDifferentChar = new MutantDto("5", dna8, false);

        List<String> dna9 = new ArrayList<>();
        dna9.add("CGGCG");
        dna9.add("CAGTC");
        dna9.add("TTATG");
        dna9.add("AGAAG");
        dna9.add("CCTCT");
        dna9.add("CGGCG");
        mutantDnaWithoutOneColumn = new MutantDto("6", dna8, false);

        List<String> dna10 = new ArrayList<>();
        dna10.add("CGG");
        dna10.add("CAG");
        dna10.add("TTA");
        mutantDnaShort = new MutantDto("7", dna9, false);
    }

    @Test
    void checkStructureDNA() {
        assertTrue(checkSequenceDNA.checkStructureDNA(goodMutantEntity));
    }

    @Test
    void checkStructureDNAWithDnaLowercase() {
        assertFalse(checkSequenceDNA.checkStructureDNA(mutantDnaWithLowercase));
    }

    @Test
    void checkStructureDNAWithoutOneChar() {
        assertFalse(checkSequenceDNA.checkStructureDNA(mutantDnaWithoutOneChar));
    }

    @Test
    void checkStructureDnaWithoutOneRow() {
        assertFalse(checkSequenceDNA.checkStructureDNA(mutantDnaWithoutOneRow));
    }

    @Test
    void checkStructureDNAWithDifferentChar() {
        assertFalse(checkSequenceDNA.checkStructureDNA(mutantDnaWithDifferentChar));
    }

    @Test
    void checkStructureDNAWithoutOneColumn() {
        assertFalse(checkSequenceDNA.checkStructureDNA(mutantDnaWithoutOneColumn));
    }

    @Test
    void checkStructureDNAShort() {
        assertFalse(checkSequenceDNA.checkStructureDNA(mutantDnaShort));
    }
}
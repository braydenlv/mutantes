package com.mercadolibre.mutantes.utils;

import com.mercadolibre.mutantes.dto.MutantDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MutantsClassifierTest {

    private MutantsClassifier mutantsClassifier = new MutantsClassifier();
    private MutantDto mutant;
    private MutantDto human;
    private List<String> dnaMutant;
    private List<String> dnaHuman;

    @BeforeEach
    void setUp() {
        dnaMutant = new ArrayList();
        dnaMutant.add("CCCC");
        dnaMutant.add("TTTT");
        dnaMutant.add("AAGG");
        dnaMutant.add("GACT");
        mutant = new MutantDto("1", dnaMutant, true);

        dnaHuman = new ArrayList();
        dnaHuman.add("AACC");
        dnaHuman.add("TTGG");
        dnaHuman.add("AACC");
        dnaHuman.add("TTGG");
        human = new MutantDto("2", dnaHuman, false);

    }

    @Test
    void testIsRealMutant() {
        assertEquals(true, mutantsClassifier.isMutant(mutant));
    }

    @Test
    void testIsFakeMutant() {
        assertEquals(false, mutantsClassifier.isMutant(human));
    }
}

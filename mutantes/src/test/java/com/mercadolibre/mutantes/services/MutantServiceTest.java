package com.mercadolibre.mutantes.services;

import com.mercadolibre.mutantes.dto.MutantDto;
import com.mercadolibre.mutantes.entity.MutantEntity;
import com.mercadolibre.mutantes.entity.StatsEntity;
import com.mercadolibre.mutantes.repositories.MutantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class MutantServiceTest {

    @MockBean
    MutantRepository mutantRepository;

    @InjectMocks
    MutantService mutantService;

    private Iterable<MutantEntity> goodMutants;
    private MutantEntity goodMutantEntity1;
    private MutantEntity goodMutantEntity2;
    private MutantEntity goodMutantEntity3;
    private MutantEntity goodMutantEntity4;
    private MutantEntity mutantWithDnaLowercase;

    @BeforeEach
    void setUp() {
        List<String> dna1 = new ArrayList<>();
        dna1.add("ATGCGA");
        dna1.add("CAGTGC");
        dna1.add("TTATGT");
        dna1.add("AGAAGG");
        dna1.add("CCCCTA");
        dna1.add("TCACTG");
        goodMutantEntity1 = new MutantEntity("1", dna1, true);

        List<String> dna2 = new ArrayList<>();
        dna2.add("AGGCGA");
        dna2.add("CAGTGC");
        dna2.add("TTATGT");
        dna2.add("AGAAGG");
        dna2.add("CCCCTA");
        dna2.add("TCACTG");
        goodMutantEntity2 = new MutantEntity("2", dna2, true);

        List<String> dna3 = new ArrayList<>();
        dna3.add("ACGCGA");
        dna3.add("CAGTGC");
        dna3.add("TTATGT");
        dna3.add("AGAAGG");
        dna3.add("GGGGTA");
        dna3.add("TCACTG");
        goodMutantEntity3 = new MutantEntity("3", dna3, true);

        List<String> dna4 = new ArrayList<>();
        dna4.add("AGGCGA");
        dna4.add("CAGTCC");
        dna4.add("TTATGT");
        dna4.add("AGAAGG");
        dna4.add("CCTCTA");
        dna4.add("TCACTG");
        goodMutantEntity4 = new MutantEntity("4", dna4, false);

        List<String> dna5 = new ArrayList<>();
        dna5.add("aGGCGA");
        dna5.add("CAGTCC");
        dna5.add("TTATGT");
        dna5.add("AGAAGG");
        dna5.add("CCTCTA");
        dna5.add("TCACTG");
        mutantWithDnaLowercase = new MutantEntity("5", dna5, false);

        goodMutants = new ArrayList<>(Arrays.asList(goodMutantEntity1, goodMutantEntity2, goodMutantEntity3, goodMutantEntity4));
    }

    @Test
    void testFindAll() {
        when(mutantRepository.findAll()).thenReturn(goodMutants);
        assertIterableEquals(goodMutants, mutantService.findAll());
    }

    @Test
    void testCorrectStructureDNAWithError() {
        MutantDto mutantDto = new MutantDto();
        mutantDto.setId(mutantWithDnaLowercase.getId());
        mutantDto.setDna(mutantWithDnaLowercase.getDna());
        mutantDto.setIsMutant(mutantWithDnaLowercase.getIsMutant());

        assertFalse(mutantService.correctStructure(mutantDto));
    }

    @Test
    void testCorrectStructureDNAHealthy() {
        MutantDto mutantDto = new MutantDto();
        mutantDto.setId(goodMutantEntity3.getId());
        mutantDto.setDna(goodMutantEntity3.getDna());
        mutantDto.setIsMutant(goodMutantEntity3.getIsMutant());

        assertTrue(mutantService.correctStructure(mutantDto));
    }


    @Test
    void testDeleteById() {
        String id = goodMutantEntity1.getId();

        doNothing().when(mutantRepository).deleteById(isA(String.class));
        mutantService.deleteById(id);
    }

    @Test
    void testExistByDnaTrue() {
        List<String> dna = goodMutantEntity2.getDna();

        when(mutantRepository.existsByDna(dna)).thenReturn(true);
        assertTrue(mutantService.existByDna(dna));
    }

    @Test
    void testExistByDnaFalse() {
        List<String> dna = goodMutantEntity4.getDna();

        when(mutantRepository.existsByDna(dna)).thenReturn(false);
        assertFalse(mutantService.existByDna(dna));
    }

    @Test
    void testExistByIdTrue() {
        String id = goodMutantEntity1.getId();

        when(mutantRepository.existsById(id)).thenReturn(true);
        assertTrue(mutantService.existById(id));
    }

    @Test
    void testExistByIdFalse() {
        String id = goodMutantEntity3.getId();

        when(mutantRepository.existsById(id)).thenReturn(false);
        assertFalse(mutantService.existById(id));
    }

    @Test
    void testFindByIdTrue() {
        String id = goodMutantEntity4.getId();

        when(mutantRepository.findById(id)).thenReturn(Optional.of(goodMutantEntity4));
        assertEquals(goodMutantEntity4, mutantService.findById(id));
    }

    @Test
    void testFindByIdFalse() {
        String id = goodMutantEntity3.getId();
        String otherId = goodMutantEntity4.getId();

        when(mutantRepository.findById(id)).thenReturn(Optional.of(goodMutantEntity3));
        assertNull(mutantService.findById(otherId));
    }

    @Test
    void testSave() {
        MutantDto mutantDto = new MutantDto();
        mutantDto.setId(goodMutantEntity2.getId());
        mutantDto.setDna(goodMutantEntity2.getDna());
        mutantDto.setIsMutant(goodMutantEntity2.getIsMutant());

        when(mutantRepository.save(any(MutantEntity.class))).thenReturn(goodMutantEntity2);
        assertEquals(goodMutantEntity2, mutantService.save(mutantDto));
    }

    @Test
    void testGetStats() {
        StatsEntity statsEntity = new StatsEntity(4L, 10L, "0,40");

        when(mutantRepository.countByIsMutant(false)).thenReturn(10L);
        when(mutantRepository.countByIsMutant(true)).thenReturn(4L);
        assertEquals(statsEntity, mutantService.getStats());
    }

    @Test
    void testGetStatsInZeros() {
        StatsEntity statsEntity = new StatsEntity(0L, 0L, "Can't split between 0.");

        when(mutantRepository.countByIsMutant(false)).thenReturn(0L);
        when(mutantRepository.countByIsMutant(true)).thenReturn(0L);
        assertEquals(statsEntity, mutantService.getStats());
    }

    @Test
    void testIsMutantTrue() {
        MutantDto mutantDto = new MutantDto();
        mutantDto.setId(goodMutantEntity2.getId());
        mutantDto.setDna(goodMutantEntity2.getDna());
        mutantDto.setIsMutant(goodMutantEntity2.getIsMutant());

        assertTrue(mutantService.isMutant(mutantDto));
    }

    @Test
    void testIsMutantFalse() {
        MutantDto mutantDto = new MutantDto();
        mutantDto.setId(goodMutantEntity4.getId());
        mutantDto.setDna(goodMutantEntity4.getDna());
        mutantDto.setIsMutant(goodMutantEntity4.getIsMutant());

        assertFalse(mutantService.isMutant(mutantDto));
    }
}

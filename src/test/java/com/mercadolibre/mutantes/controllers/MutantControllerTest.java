package com.mercadolibre.mutantes.controllers;

import com.mercadolibre.mutantes.dto.MutantDto;
import com.mercadolibre.mutantes.entity.MutantEntity;
import com.mercadolibre.mutantes.entity.StatsEntity;
import com.mercadolibre.mutantes.services.MutantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = MutantController.class)
class MutantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MutantService mutantService;

    private MutantEntity mutant1;
    private MutantEntity mutant2;
    private MutantEntity human;
    private Iterable<MutantEntity> mutants;

    @BeforeEach
    void setUp() {
        List<String> dna1 = new ArrayList<>();
        dna1.add("ATGCG");
        dna1.add("CAGTG");
        dna1.add("TTATG");
        dna1.add("AGAAG");
        dna1.add("CCCCT");
        mutant1 = new MutantEntity("1", dna1, true);

        List<String> dna2 = new ArrayList<>();
        dna2.add("AGGCG");
        dna2.add("CAGTG");
        dna2.add("TTATG");
        dna2.add("AGAAG");
        dna2.add("CCCCT");
        mutant2 = new MutantEntity("2", dna2, true);

        List<String> dna3 = new ArrayList<>();
        dna3.add("AGGCG");
        dna3.add("CTGTG");
        dna3.add("TTATA");
        dna3.add("AGAAG");
        dna3.add("CCTCT");
        human = new MutantEntity("3", dna3, false);

        mutants = new ArrayList<>(Arrays.asList(mutant1, mutant2, human));
    }

    @Test
    void listWithMutants() throws Exception {
        String listMutantsJson = "[{\"id\":\"1\",\"dna\":[\"ATGCG\",\"CAGTG\",\"TTATG\",\"AGAAG\",\"CCCCT\"],\"isMutant\":true}," +
                "{\"id\":\"2\",\"dna\":[\"AGGCG\",\"CAGTG\",\"TTATG\",\"AGAAG\",\"CCCCT\"],\"isMutant\":true}," +
                "{\"id\":\"3\",\"dna\":[\"AGGCG\",\"CTGTG\",\"TTATA\",\"AGAAG\",\"CCTCT\"],\"isMutant\":false}]";

        when(mutantService.findAll()).thenReturn(mutants);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/mutant").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(listMutantsJson, result.getResponse().getContentAsString(), false);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void listWithoutMutants() throws Exception {
        String listMutantsJson = "[]";
        Iterable<MutantEntity> withoutMutants = new ArrayList<>();

        when(mutantService.findAll()).thenReturn(withoutMutants);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/mutant").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(listMutantsJson, result.getResponse().getContentAsString(), false);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void listStats() throws Exception {
        String statsJson = "{\"count_mutant_dna\":2,\"count_human_dna\":1,\"ratio\":\"0.50\"}";

        when(mutantService.getStats()).thenReturn(new StatsEntity(2L, 1L, "0.50"));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stats").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(statsJson, result.getResponse().getContentAsString(), false);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void findByIdWhenExists() throws Exception {
        String individualMutantJson = "{\"id\":\"1\",\"dna\":[\"ATGCG\",\"CAGTG\",\"TTATG\",\"AGAAG\",\"CCCCT\"],\"isMutant\":true}";
        String id = mutant1.getId();

        when(mutantService.findById(id)).thenReturn(mutant1);
        when(mutantService.existById(id)).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/mutant/{id}", id).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(individualMutantJson, result.getResponse().getContentAsString(), false);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void findByIdWhenNotExists() throws Exception {
        String mutantDoesNotExistJson = "Mutant not found.";
        String id = mutant1.getId();

        when(mutantService.existById(any(String.class))).thenReturn(false);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/mutant/{id}", id).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
        assertEquals(mutantDoesNotExistJson, result.getResponse().getContentAsString());
    }

    @Test
    void saveWithMutant() throws Exception {
        String individualMutantJson = "{\"id\":\"2\",\"dna\":[\"AGGCG\",\"CAGTG\",\"TTATG\",\"AGAAG\",\"CCCCT\"]}";
        String individualMutantExpectJson = "{\"id\":\"2\",\"dna\":[\"AGGCG\",\"CAGTG\",\"TTATG\",\"AGAAG\",\"CCCCT\"],\"isMutant\":true}";
        MutantDto mutant = new MutantDto();
        mutant.setId(mutant2.getId());
        mutant.setDna(mutant2.getDna());
        mutant.setIsMutant(null);

        when(mutantService.existByDna(any(List.class))).thenReturn(false);
        when(mutantService.correctStructure(any(MutantDto.class))).thenReturn(true);
        when(mutantService.save(any(MutantDto.class))).thenReturn(mutant2);
        when(mutantService.isMutant(any(MutantDto.class))).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/mutant").accept(MediaType.APPLICATION_JSON).content(individualMutantJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(individualMutantExpectJson, result.getResponse().getContentAsString(), false);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void saveWithHuman() throws Exception {
        String individualMutantJson = "{\"id\":\"3\",\"dna\":[\"AGGCG\",\"CTGTG\",\"TTATA\",\"AGAAG\",\"CCTCT\"]}";
        String individualMutantExpectJson = "{\"id\":\"3\",\"dna\":[\"AGGCG\",\"CTGTG\",\"TTATA\",\"AGAAG\",\"CCTCT\"],\"isMutant\":false}";
        MutantDto mutant = new MutantDto();
        mutant.setId(mutant2.getId());
        mutant.setDna(mutant2.getDna());
        mutant.setIsMutant(null);

        when(mutantService.existByDna(any(List.class))).thenReturn(false);
        when(mutantService.correctStructure(any(MutantDto.class))).thenReturn(true);
        when(mutantService.save(any(MutantDto.class))).thenReturn(human);
        when(mutantService.isMutant(any(MutantDto.class))).thenReturn(false);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/mutant").accept(MediaType.APPLICATION_JSON).content(individualMutantJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(individualMutantExpectJson, result.getResponse().getContentAsString(), false);
        assertEquals(HttpStatus.FORBIDDEN.value(), result.getResponse().getStatus());
    }

    @Test
    void saveWithSameMutantDNA() throws Exception {
        String individualMutantJson = "{\"id\":\"2\",\"dna\":[\"AGGCG\",\"CAGTG\",\"TTATG\",\"AGAAG\",\"CCCCT\"]}";
        String mutantAlreadyExist = "Mutant already exist.";
        MutantDto mutant = new MutantDto();
        mutant.setId(mutant2.getId());
        mutant.setDna(mutant2.getDna());
        mutant.setIsMutant(null);

        when(mutantService.existByDna(any(List.class))).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/mutant").accept(MediaType.APPLICATION_JSON).content(individualMutantJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(mutantAlreadyExist, result.getResponse().getContentAsString());
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }

    @Test
    void saveWithoutGoodDNAExtructure() throws Exception {
        String individualMutantJson = "{\"id\":\"2\",\"dna\":[\"AGGCG\",\"CAGTG\",\"TTATG\",\"AGAAG\",\"CCCCT\"]}";
        String TheInformationIsWrong = "The information is wrong. Please verify the input and try again.";
        MutantDto mutant = new MutantDto();
        mutant.setId(mutant2.getId());
        mutant.setDna(mutant2.getDna());
        mutant.setIsMutant(null);

        when(mutantService.existByDna(any(List.class))).thenReturn(false);
        when(mutantService.correctStructure(any(MutantDto.class))).thenReturn(false);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/mutant").accept(MediaType.APPLICATION_JSON).content(individualMutantJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(TheInformationIsWrong, result.getResponse().getContentAsString());
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }

    @Test
    void deleteByIdWhenIdExists() throws Exception {
        String mutantDeleted = "Mutant deleted.";
        String id = human.getId();

        when(mutantService.existById(any(String.class))).thenReturn(true);
        doNothing().when(mutantService).deleteById(any(String.class));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/mutant/{id}", id).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(mutantDeleted, result.getResponse().getContentAsString());
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

    }

    @Test
    void deleteByIdWhenIdDoesNotExists() throws Exception {
        String mutantNotFound = "Mutant not found.";
        String id = human.getId();

        when(mutantService.existById(any(String.class))).thenReturn(false);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/mutant/{id}", id).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(mutantNotFound, result.getResponse().getContentAsString());
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());

    }
}
package com.mercadolibre.mutantes.services;

import com.mercadolibre.mutantes.dto.MutantDto;
import com.mercadolibre.mutantes.entity.MutantEntity;
import com.mercadolibre.mutantes.entity.StatsEntity;
import com.mercadolibre.mutantes.repositories.MutantRepository;
import com.mercadolibre.mutantes.utils.CheckSequenceDNA;
import com.mercadolibre.mutantes.utils.MutantsClassifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;


@Service
public class MutantService {

    @Autowired
    MutantRepository mutantRepository;

    public Iterable<MutantEntity> findAll() {
        return mutantRepository.findAll();
    }

    public MutantEntity findById(String id) {
        return mutantRepository.findById(id).isPresent() ? mutantRepository.findById(id).get() : null;
    }

    public boolean existById(String id) {
        return mutantRepository.existsById(id);
    }

    public boolean existByDna(List<String> dna) {
        return mutantRepository.existsByDna(dna);
    }

    public MutantEntity save(MutantDto mutantDto) {
        MutantEntity mutant = new MutantEntity();
        if (mutantDto.getId() != null) {
            mutant.setId(mutantDto.getId());
        }
        mutant.setDna(mutantDto.getDna());
        mutant.setIsMutant(mutantDto.getIsMutant());

        return mutantRepository.save(mutant);
    }

    public boolean isMutant(MutantDto mutantDto) {
        MutantsClassifier mutantsClassifier = new MutantsClassifier();

        return mutantsClassifier.isMutant(mutantDto);
    }

    public void deleteById(String id) {
        mutantRepository.deleteById(id);
    }

    public boolean correctStructure(MutantDto mutantDto) {
        CheckSequenceDNA checkSequenceDNA = new CheckSequenceDNA();
        return checkSequenceDNA.checkStructureDNA(mutantDto);
    }

    public StatsEntity getStats() {
        String ratio;
        Long numberOfHumans = mutantRepository.countByIsMutant(false);
        Long numberOfMutants = mutantRepository.countByIsMutant(true);

        if (numberOfHumans == 0) {
            ratio = "Can't split between 0.";
        } else {
            DecimalFormat df = new DecimalFormat("0.00");
            ratio = String.valueOf(df.format((double) numberOfMutants / numberOfHumans));
        }

        return new StatsEntity(numberOfMutants, numberOfHumans, ratio);
    }
}

package com.mercadolibre.mutantes.controllers;

import com.mercadolibre.mutantes.dto.MutantDto;
import com.mercadolibre.mutantes.entity.MutantEntity;
import com.mercadolibre.mutantes.entity.StatsEntity;
import com.mercadolibre.mutantes.services.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SuppressWarnings({"rawtypes", "unchecked"})
public class MutantController {

    @Autowired
    MutantService mutantService;

    @GetMapping("/mutant")
    public ResponseEntity<Iterable<MutantEntity>> listMutants() {
        return ResponseEntity.ok(mutantService.findAll());
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsEntity> listStats() {
        return ResponseEntity.ok(mutantService.getStats());
    }


    @GetMapping("/mutant/{id}")
    public ResponseEntity<MutantEntity> findById(@PathVariable("id") String id) {
        if (!mutantService.existById(id)) {
            return new ResponseEntity("Mutant not found.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(mutantService.findById(id));
    }


    @PostMapping("/mutant")
    public ResponseEntity<MutantEntity> save(@RequestBody MutantDto mutantDto) {
        if (mutantService.existByDna(mutantDto.getDna())) {
            return new ResponseEntity("Mutant already exist.", HttpStatus.BAD_REQUEST);
        }

        if (!mutantService.correctStructure(mutantDto)) {
            return new ResponseEntity("The information is wrong. Please verify the input and try again.", HttpStatus.BAD_REQUEST);
        }

        mutantDto.setIsMutant(mutantService.isMutant(mutantDto));

        if (mutantDto.getIsMutant()) {
            return ResponseEntity.ok(mutantService.save(mutantDto));
        } else {
            return new ResponseEntity<>(mutantService.save(mutantDto), HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/mutant/{id}")
    public ResponseEntity<MutantEntity> deleteById(@PathVariable("id") String id) {
        if (!mutantService.existById(id)) {
            return new ResponseEntity("Mutant not found.", HttpStatus.NOT_FOUND);
        }
        mutantService.deleteById(id);
        return new ResponseEntity("Mutant deleted.", HttpStatus.OK);
    }

}

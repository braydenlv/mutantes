package com.mercadolibre.mutantes.repositories;

import com.mercadolibre.mutantes.entity.MutantEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableScan
@EnableScanCount
@Repository
public interface MutantRepository extends CrudRepository<MutantEntity, String> {
    boolean existsByDna(List<String> dna);

    Long countByIsMutant(Boolean isMutant);
}

package com.example.mutante.service;

import com.example.mutante.dto.StatsResponse;
import com.example.mutante.repository.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
    @Autowired
    private DnaRepository dnaRepository;

    public StatsResponse getStats() {
        //Buscamos en la BD cuantos mutantes y humanos hay
        long countMutant = dnaRepository.countByIsMutant(true);
        long countHuman = dnaRepository.countByIsMutant(false);

        //Calculamos el ratio
        double ratio = 0.0;

        if (countHuman > 0) {
            ratio = (double) countMutant / countHuman;
        }
        return new StatsResponse(countMutant, countHuman, ratio);
    }
}

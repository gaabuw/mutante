package com.example.mutante.service;

import com.example.mutante.model.Dna;
import com.example.mutante.repository.DnaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DnaServiceTest {

    @InjectMocks
    private DnaService dnaService;

    @Mock
    private DnaRepository dnaRepository;

    //Tests

    @Test //mutante
    public void testIsMutant_Detected(){
        String[] mutantDna = {
                "AAAA",
                "CCCC",
                "TCAG",
                "GGTC"
        };
        assertTrue(dnaService.isMutant(mutantDna));
    }

    @Test //mutante
    public void testIsMutant_Diagonals() {
        String[] mutantDna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(dnaService.isMutant(mutantDna));
    }

    @Test //humano
    public void testisHuman(){
        String[] humanDna = {
                "AAAT",
                "AACC",
                "AAAC",
                "CGGG"
        };
        assertFalse(dnaService.isMutant(humanDna));
    }

    @Test //Analyze nuevo(ANalizamos y guardamos...)
    public void testAnalyze_NewDna_Mutant(){
        String[] dna = {"AAAA", "CCCC", "TCAG", "GGTC"};
        String sequenceStr = "AAAA,CCCC,TCAG,GGTC";

        //Simulamos que NO existe en la base de datos (opt vacio)
        when(dnaRepository.findBySecuencia(sequenceStr)).thenReturn(Optional.empty());

        //Ejecutamos
        boolean result = dnaService.analyze(dna);

        //Verificamos que dio true
        assertTrue(result);

        //Verificamos que se llamo al save
        verify(dnaRepository).save(any(Dna.class));
    }

    @Test //Analyze (ya existente)
    public void testAnalyze_ExistingDna() {
        String[] dna = {"AAAA", "CCCC", "TCAG", "GGTC"};
        String sequenceStr = "AAAA,CCCC,TCAG,GGTC";

        //Simulamos que YA existe y que era mutante
        Dna existingDna = new Dna();
        existingDna.setMutant(true);
        when(dnaRepository.findBySecuencia(sequenceStr)).thenReturn(Optional.of(existingDna));

        //Ejecutamos
        boolean result = dnaService.analyze(dna);

        //Verificamos que devolvio true
        assertTrue(result);
        verify(dnaRepository, times(0)).save(any(Dna.class));
    }

}

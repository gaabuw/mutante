package com.example.mutante.service;

import com.example.mutante.repository.DnaRepository;
import com.example.mutante.model.Dna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DnaService {

    @Autowired
    private DnaRepository dnaRepository;
    private static final int SEQUENCE_LENGTH = 4;

    //Analiza y guarda
    public boolean analyze(String[] dna) {
        String sequenceStr = String.join(",", dna);

        //Verificamos si existe
        Optional<Dna> existingDna = dnaRepository.findBySecuencia(sequenceStr);
        if (existingDna.isPresent()) {
            return existingDna.get().isMutant();
        }

        //Calculamos
        boolean isMutant = isMutant(dna);

        //Guardamos
        Dna dnaEntity = new Dna();
        dnaEntity.setSecuencia(sequenceStr);
        dnaEntity.setMutant(isMutant);
        dnaRepository.save(dnaEntity);

        return isMutant;
    }


    //LOGICA
    public boolean isMutant(String[] dna){
        if(dna == null || dna.length == 0){
            throw new IllegalArgumentException("El DNA no puede ser nulo ni vacío.");
        }
        int n = dna.length;
        int sequenceCount = 0; //Contador de Secuencias

        //Verificación de la matriz
        for (String row : dna) {
            if (row == null || row.length() != n) {
                throw new IllegalArgumentException("La matriz debe ser cuadrada (NxN).");
            }
            if (!row.matches("[ATCG]+")) { // Regex para validar solo A, T, C, G
                throw new IllegalArgumentException("El ADN contiene caracteres inválidos.");
            }
        }


        //Recorremos la matriz
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <n; j++) {

                if (sequenceCount > 1) {
                    return true;
                }

                //horizontal desde la derecha
                if (j + SEQUENCE_LENGTH <= n) {
                    if (check(dna, i, j, 0, 1)) {
                        sequenceCount++;
                    }
                }

                //Vertical (abajo)
                if (i + SEQUENCE_LENGTH <= n) {
                    if (check(dna, i, j, 1, 0)) {
                        sequenceCount++;
                    }
                }

                //Diagonal principal (abajo-derecha)
                if (i + SEQUENCE_LENGTH <= n && j + SEQUENCE_LENGTH <= n) {
                    if (check(dna, i, j, 1, 1)) {
                        sequenceCount++;
                    }
                }

                //Diagonal secundaria (abajo-izq)
                if (i + SEQUENCE_LENGTH <= n && j - SEQUENCE_LENGTH >= -1) {
                    if (check(dna, i, j, 1, -1)) {
                        sequenceCount++;
                    }
                }
            }
        }

        return sequenceCount > 1;
    }

    //Metodo Check
    private boolean check(String[] dna, int row, int col, int dy, int dx) {
        char first = dna[row].charAt(col);

        //Verificamos las 3 letras siguientes
        for (int k = 1; k < SEQUENCE_LENGTH; k++) {
            // Calculamos la posición de la siguiente celda
            int nextRow = row + dy * k;
            int nextCol = col + dx * k;

            //Si la letra no coincide, rompemos el ciclo
            if (dna[nextRow].charAt(nextCol) != first) {
                return false;
            }
        }
        //Si llegamos aca, las 4 letras eran iguales
        return true;
    }
}


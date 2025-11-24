package com.example.mutante.repository;

import com.example.mutante.model.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DnaRepository extends JpaRepository<Dna, Long> {
    Optional<Dna> findBySecuencia(String secuencia);
    long countByIsMutant(boolean isMutant);
}

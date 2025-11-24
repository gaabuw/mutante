package com.example.mutante.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Dna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String secuencia;

    private boolean isMutant;
}

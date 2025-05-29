package com.RodriSolution.SpringPetMagro.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @Embedded
    private Endereco endereco;
    private Double idade;
    private Double peso;
    private String raca;
    private LocalDate data;


}

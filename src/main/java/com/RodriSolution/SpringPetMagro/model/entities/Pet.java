package com.RodriSolution.SpringPetMagro.model.entities;

import com.RodriSolution.SpringPetMagro.model.enums.Sexo;
import com.RodriSolution.SpringPetMagro.model.enums.Tipo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    private String petNome;
    private String lastnamePet;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @Embedded
    private Endereco endereco;
    private Double idade;
    private Double peso;
    private String raca;
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "tutor_id",nullable = false)
    private Tutor tutor;

    //teste unitario(apagar depois)
    public Pet(String petNome, Tutor tutor) {
        this.petNome = petNome;
        this.tutor = tutor;
    }
}

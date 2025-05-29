package com.RodriSolution.SpringPetMagro.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Endereco {
    private String cidade;
    private String rua;
    private String numero;
}

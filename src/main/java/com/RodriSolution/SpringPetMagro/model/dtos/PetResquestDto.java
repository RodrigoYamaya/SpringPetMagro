package com.RodriSolution.SpringPetMagro.model.dtos;

import com.RodriSolution.SpringPetMagro.model.enums.Sexo;
import com.RodriSolution.SpringPetMagro.model.enums.Tipo;
import com.RodriSolution.SpringPetMagro.model.entities.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record PetResquestDto(
                           @NotNull Tipo tipo,
                           @NotBlank String petNome,
                           @NotBlank String lastnamePet ,
                           @NotNull Sexo sexo,
                           @NotNull Endereco endereco,
                           @Positive Double idade,
                           @Positive Double peso,
                           @NotBlank String raca,
                           @NotNull Long tutor,
                           @PastOrPresent LocalDate data) {
}

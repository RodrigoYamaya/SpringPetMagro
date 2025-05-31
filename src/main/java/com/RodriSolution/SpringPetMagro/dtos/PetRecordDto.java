package com.RodriSolution.SpringPetMagro.dtos;

import com.RodriSolution.SpringPetMagro.model.Endereco;
import com.RodriSolution.SpringPetMagro.model.Sexo;
import com.RodriSolution.SpringPetMagro.model.Tipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;


import java.time.LocalDate;

public record PetRecordDto(
                           @NotNull Tipo tipo,
                           @NotBlank String firstName,
                           @NotBlank String lastName,
                           @NotNull Sexo sexo,
                           @NotNull Endereco endereco,
                           @Positive Double idade,
                           @Positive Double peso,
                           @NotBlank String raca,
                           @PastOrPresent LocalDate data) {
}

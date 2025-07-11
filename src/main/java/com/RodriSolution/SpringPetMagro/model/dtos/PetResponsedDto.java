package com.RodriSolution.SpringPetMagro.model.dtos;

import com.RodriSolution.SpringPetMagro.model.entities.Endereco;
import com.RodriSolution.SpringPetMagro.model.enums.Sexo;
import com.RodriSolution.SpringPetMagro.model.enums.Tipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;


import java.time.LocalDate;

public record PetResponsedDto(
                           @NotNull  Long id,
                           @NotNull Tipo tipo,
                           @NotBlank String petNome,
                           @NotBlank String lastnamePet ,
                           @NotNull Sexo sexo,
                           @NotNull Endereco endereco,
                           @Positive Double idade,
                           @Positive Double peso,
                           @NotBlank String raca,
                           @NotNull TutorResponseDto tutor,
                           @PastOrPresent LocalDate data) {
}

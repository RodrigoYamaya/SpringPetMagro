package com.RodriSolution.SpringPetMagro.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TutorRequestDto(
        @NotNull
        String nome,
        @NotBlank
        String email,
        @NotBlank
        String celular
) {
}

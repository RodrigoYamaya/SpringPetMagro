package com.RodriSolution.SpringPetMagro.dtos;

import jakarta.validation.constraints.NotBlank;

public record loginDtoRequest(@NotBlank String username,
                              @NotBlank String password) {
}

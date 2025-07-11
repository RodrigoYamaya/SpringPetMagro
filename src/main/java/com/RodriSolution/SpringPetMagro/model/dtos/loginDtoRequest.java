package com.RodriSolution.SpringPetMagro.model.dtos;

import jakarta.validation.constraints.NotBlank;

public record loginDtoRequest(@NotBlank String username,
                              @NotBlank String password) {
}

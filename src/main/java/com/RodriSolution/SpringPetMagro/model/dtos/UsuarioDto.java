package com.RodriSolution.SpringPetMagro.model.dtos;

import com.RodriSolution.SpringPetMagro.model.enums.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDto(
        @NotBlank String username,
        @NotBlank String password,
        @NotNull RoleEnum role


) {
}

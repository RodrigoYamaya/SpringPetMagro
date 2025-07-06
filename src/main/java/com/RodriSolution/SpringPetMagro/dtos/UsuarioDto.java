package com.RodriSolution.SpringPetMagro.dtos;

import com.RodriSolution.SpringPetMagro.enums.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDto(
        @NotBlank String username,
        @NotBlank String password,
        @NotNull RoleEnum role


) {
}

package com.RodriSolution.SpringPetMagro.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TutorRequestDto(
        @NotBlank(message = "Nome é Obrigatório")
        @Pattern(regexp = "^[A-Za-zÀ-ú]+(\\s[A-Za-zÀ-ú]+)+$", message = "Informe o nome completo com sobrenome")
        String nome,
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,
        @NotBlank(message = "O telefone é obrigatório")
        @Pattern(regexp = "\\(\\d{2}\\)\\d{4,5}-\\d{4}", message = "Formato de telefone inválido. Ex: (21) 94002-8922")
        String celular
) {
}

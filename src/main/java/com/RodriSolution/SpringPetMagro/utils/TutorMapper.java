package com.RodriSolution.SpringPetMagro.utils;

import com.RodriSolution.SpringPetMagro.model.dtos.TutorRequestDto;
import com.RodriSolution.SpringPetMagro.model.dtos.TutorResponseDto;
import com.RodriSolution.SpringPetMagro.model.entities.Tutor;
import org.springframework.stereotype.Component;

@Component
public class TutorMapper {

    public static Tutor toEntity(TutorRequestDto tutorRequestDto) {
        if(tutorRequestDto == null) {
            throw new IllegalArgumentException("Informações passadas erradas");
        }
        Tutor tutor = new Tutor();
        tutor.setNome(tutorRequestDto.nome());
        tutor.setEmail(tutorRequestDto.email());
        tutor.setCelular(tutorRequestDto.celular());
        return tutor;

    }

    public static TutorResponseDto toDto(Tutor tutor) {
        if(tutor == null) {
            throw new IllegalArgumentException("Informações passadas erradas");
        }
        return new TutorResponseDto(
                tutor.getId(),
                tutor.getNome(),
                tutor.getEmail(),
                tutor.getCelular());

    }
}

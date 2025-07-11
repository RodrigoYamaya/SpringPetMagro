package com.RodriSolution.SpringPetMagro.services;


import com.RodriSolution.SpringPetMagro.model.dtos.TutorRequestDto;
import com.RodriSolution.SpringPetMagro.model.dtos.TutorResponseDto;
import com.RodriSolution.SpringPetMagro.exceptions.RecursoDuplicadoException;
import com.RodriSolution.SpringPetMagro.exceptions.RecursoNaoEncontrado;
import com.RodriSolution.SpringPetMagro.model.entities.Tutor;
import com.RodriSolution.SpringPetMagro.repositories.TutorRepository;
import com.RodriSolution.SpringPetMagro.utils.TutorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorService {
    @Autowired
    private TutorRepository TutorRepository;

    @Transactional
    public TutorResponseDto salvarTutor(TutorRequestDto dto) {
        if(TutorRepository.existsByNomeIgnoreCase(dto.nome())) {
            throw new RecursoDuplicadoException("ja existe um tutor com esse nome");
        }
        Tutor tutor = TutorMapper.toEntity(dto);
        TutorRepository.save(tutor);
        return TutorMapper.toDto(tutor);

    }

    public Tutor buscarTutorPorId(Long id) {
        return TutorRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Tutor nao encontrado"));


    }

    public List<TutorResponseDto> listarTutores() {
        List<Tutor> tutoresAll = TutorRepository.findAll();
        if(tutoresAll.isEmpty()) {
            throw new RecursoNaoEncontrado("NÃO EIXTSE TUTORES");       }

        return tutoresAll.stream()
                .map(TutorMapper::toDto)
                .collect(Collectors.toList());
    }



}

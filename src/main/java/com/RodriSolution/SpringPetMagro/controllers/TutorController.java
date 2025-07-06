package com.RodriSolution.SpringPetMagro.controllers;

import com.RodriSolution.SpringPetMagro.dtos.TutorRequestDto;
import com.RodriSolution.SpringPetMagro.dtos.TutorResponseDto;
import com.RodriSolution.SpringPetMagro.model.Tutor;
import com.RodriSolution.SpringPetMagro.services.TutorService;
import com.RodriSolution.SpringPetMagro.utils.TutorMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TutorController {
    @Autowired
    TutorService tutorService;

    @PostMapping("/tutor")
    public ResponseEntity<TutorResponseDto> saveTutor(@RequestBody @Valid TutorRequestDto dto) {
        TutorResponseDto tutorSave = tutorService.salvarTutor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tutorSave);

    }
    @GetMapping("/tutor/{id}")
    public ResponseEntity<TutorResponseDto> tutorById(@PathVariable(value = "id") long id) {
        Tutor tutor = tutorService.buscarTutorPorId(id);
        return ResponseEntity.ok(TutorMapper.toDto(tutor));

    }

    @GetMapping("/tutor/lista")
    public ResponseEntity<List<TutorResponseDto>> getALLTutor(){
        return ResponseEntity.ok(tutorService.listarTutores());
    }


}

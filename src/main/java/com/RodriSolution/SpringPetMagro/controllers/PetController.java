package com.RodriSolution.SpringPetMagro.controllers;

import com.RodriSolution.SpringPetMagro.dtos.PetRecordDto;
import com.RodriSolution.SpringPetMagro.model.Pet;
import com.RodriSolution.SpringPetMagro.repositories.PetRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetController {

    @Autowired
    PetRepository petRepository;

    @PostMapping("/pets")
    @Transactional
    public ResponseEntity<Pet> savePet(@RequestBody  @Valid PetRecordDto petRecordDto) {
        var pet = new Pet();
        BeanUtils.copyProperties(petRecordDto, pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(petRepository.save(pet));

    }

}

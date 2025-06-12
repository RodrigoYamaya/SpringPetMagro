package com.RodriSolution.SpringPetMagro.controllers;

import com.RodriSolution.SpringPetMagro.dtos.PetRecordDto;
import com.RodriSolution.SpringPetMagro.model.Pet;
import com.RodriSolution.SpringPetMagro.model.Sexo;
import com.RodriSolution.SpringPetMagro.model.Tipo;
import com.RodriSolution.SpringPetMagro.repositories.PetRepository;
import com.RodriSolution.SpringPetMagro.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
public class PetController {

    @Autowired
    PetService petService;



    @PostMapping("/pets")
    @Transactional
    public ResponseEntity<Pet> savePet(@RequestBody @Valid PetRecordDto petRecordDto) {
        var pet = new Pet();
        BeanUtils.copyProperties(petRecordDto, pet);
        petService.salvarPet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(pet);

    }

    @GetMapping("/pets/lista")
    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = petService.listarPets();
        return ResponseEntity.status(HttpStatus.OK).body(pets);
    }

    @GetMapping("/pets/busca")
    public ResponseEntity<List<Pet>> getPetFiltro(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String petNome,
            @RequestParam(required = false) String lastnamePet,
            @RequestParam(required = false) Double idade,
            @RequestParam(required = false) String raca,
            @RequestParam(required = false) String endereco,
            @RequestParam(required = false) Tipo tipo,
            @RequestParam(required = false) Sexo sexo,
            @RequestParam(required = false) Double peso
            ){

        List<Pet> petsFiltrados = petService.buscaPorFiltro(id, petNome,lastnamePet, tipo, sexo, endereco,idade,peso,raca);
        return ResponseEntity.ok(petsFiltrados);
    }


    @PutMapping("/pets/{id}")
    public ResponseEntity<Object> updatePet(@PathVariable(value = "id") long id, @RequestBody @Valid PetRecordDto petRecordDto) {

        Optional<Pet> pet0 = petService.findById(id);
        if (pet0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found");
        }

        var pet = pet0.get();
        BeanUtils.copyProperties(petRecordDto, pet);
        petService.salvarPet(pet);
        return ResponseEntity.status(HttpStatus.OK).body(pet);


    }

    @DeleteMapping("/pets/{id}")
    @Transactional
    public ResponseEntity<Object> deletePet(@PathVariable(value = "id") long id) {

        Optional<Pet> pet0 = petService.findById(id);
        if (pet0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found");
        }
        petService.deletarPet(id);
        return ResponseEntity.status(HttpStatus.OK).body("pet deleted sucessfully");


    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<Object> petBuscaId(@PathVariable(value = "id") long id) {
        Optional<Pet> pet0 = petService.findById(id);

        if (pet0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id not found");
        }
        Pet pet = pet0.get();
        return ResponseEntity.status(HttpStatus.OK).body(pet);
    }
}
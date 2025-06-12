package com.RodriSolution.SpringPetMagro.controllers;

import com.RodriSolution.SpringPetMagro.dtos.PetRecordDto;
import com.RodriSolution.SpringPetMagro.exceptions.RecursoNaoEncontrado;
import com.RodriSolution.SpringPetMagro.model.Pet;
import com.RodriSolution.SpringPetMagro.model.Sexo;
import com.RodriSolution.SpringPetMagro.model.Tipo;
import com.RodriSolution.SpringPetMagro.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.List;


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

        try {
            Pet pet0 = petService.findById(id);
            var pet = pet0;
            BeanUtils.copyProperties(petRecordDto, pet);
            petService.salvarPet(pet);
            return ResponseEntity.status(HttpStatus.OK).body(pet);
        } catch (RecursoNaoEncontrado e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found");

        }

    }

    @DeleteMapping("/pets/{id}")
    @Transactional
    public ResponseEntity<?> deletePet(@PathVariable(value = "id") long id) {
        try {
            petService.deletarPet(id);
            return ResponseEntity.status(HttpStatus.OK).body("Pet com o ID " + id + " deletado com sucesso.");
        }
        catch (RecursoNaoEncontrado e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet com o ID " + id + " não encontrado.");

        }
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<?> petBuscaId(@PathVariable(value = "id") long id) {
        try {
            Pet pet = petService.findById(id);
            return ResponseEntity.ok(pet);
        }
        catch (RecursoNaoEncontrado e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());


        }

    }
}
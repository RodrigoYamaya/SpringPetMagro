package com.RodriSolution.SpringPetMagro.controllers;


import com.RodriSolution.SpringPetMagro.model.dtos.PetResponsedDto;
import com.RodriSolution.SpringPetMagro.model.dtos.PetResquestDto;
import com.RodriSolution.SpringPetMagro.model.entities.Pet;
import com.RodriSolution.SpringPetMagro.model.enums.Sexo;
import com.RodriSolution.SpringPetMagro.model.enums.Tipo;
import com.RodriSolution.SpringPetMagro.services.PetService;
import com.RodriSolution.SpringPetMagro.utils.PetMapper;
import jakarta.validation.Valid;
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

    @Transactional
    @PostMapping("/pets")
    public ResponseEntity<PetResponsedDto> savePet(@RequestBody @Valid PetResquestDto dto) {
      PetResponsedDto petSalvo = petService.savePet(dto);
      return ResponseEntity.status(HttpStatus.CREATED).body(petSalvo);
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
            @RequestParam(required = false) Double peso) {

        List<Pet> petsFiltrados = petService.buscaPorFiltro(id, petNome, lastnamePet, tipo, sexo, endereco, idade, peso, raca);
        return ResponseEntity.ok(petsFiltrados);
    }
    @Transactional
    @PutMapping("/pets/{id}")
    public ResponseEntity<PetResponsedDto> updatePet(@PathVariable(value = "id") long id,
                                       @RequestBody @Valid PetResquestDto dto) {

        PetResponsedDto petUpdate = petService.updatePet(id,dto);
        return ResponseEntity.status(HttpStatus.OK).body(petUpdate);
    }
    @Transactional
    @DeleteMapping("/pets/{id}")
    public ResponseEntity<?> deletePet(@PathVariable(value = "id") long id) {
        petService.deletarPet(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pet com o ID " + id + " deletado com sucesso.");
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<?> petBuscaId(@PathVariable(value = "id") long id) {
        Pet pet = petService.findById(id);
        return ResponseEntity.ok(PetMapper.toDto(pet));
    }
}

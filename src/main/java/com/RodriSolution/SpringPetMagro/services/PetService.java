package com.RodriSolution.SpringPetMagro.services;

import com.RodriSolution.SpringPetMagro.model.Pet;
import com.RodriSolution.SpringPetMagro.model.Tipo;
import com.RodriSolution.SpringPetMagro.repositories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PetService {
    private final PetRepository petRepository;

    public void salvarPet(Pet pet) {
        petRepository.save(pet);
    }

    public List<Pet> listarPets() {
        return petRepository.findAll();
    }



    public List<Pet> buscarPetsPorFiltros(String petNome, String raca, Double idade) {
        return petRepository.findByPetNomeAndRacaAndIdade(petNome, raca, idade);
    }

    public Optional<Pet> findById(long id) {
        return petRepository.findById(id);


    }

    public void deletarPet(long id) {
        petRepository.deleteById(id);
    }

}

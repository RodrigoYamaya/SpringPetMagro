package com.RodriSolution.SpringPetMagro.services;

import com.RodriSolution.SpringPetMagro.model.Pet;
import com.RodriSolution.SpringPetMagro.model.Sexo;
import com.RodriSolution.SpringPetMagro.model.Tipo;
import com.RodriSolution.SpringPetMagro.repositories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public List<Pet> buscaPorFiltro(Long id, String petNome, String lastnamePet, Tipo tipo, Sexo sexo, String endereco, Double idade, Double peso, String raca) {


        List<Pet> pets = petRepository.findAll();
        List<Pet> petFilters = pets.stream()
                .filter(p -> (id == null) || id.equals(p.getId()))
                .filter(p -> petNome == null || p.getPetNome() != null && p.getPetNome().toLowerCase().contains(petNome.toLowerCase()))
                .filter(p -> lastnamePet == null || p.getLastnamePet() != null && p.getLastnamePet().toLowerCase().contains(lastnamePet.toLowerCase()))
                .filter(p -> tipo == null || p.getTipo() == tipo)
                .filter(p -> sexo == null || (p.getSexo() == sexo))
                .filter(p -> idade == null || (p.getIdade() != null && p.getIdade().equals(idade)))
                .filter(p -> endereco == null || endereco.isBlank() || (p.getEndereco() != null && p.getEndereco().equals(endereco)))
                .filter(p -> peso == null || (p.getPeso() != null && p.getPeso().equals(peso)))
                .filter(p -> raca == null || raca.isBlank() || (p.getRaca() != null && p.getRaca().equalsIgnoreCase(raca)))

                .collect(Collectors.toList());
        return petFilters;

    }

    public Optional<Pet> findById(long id) {
        return petRepository.findById(id);


    }

    public void deletarPet(long id) {
        petRepository.deleteById(id);
    }


}

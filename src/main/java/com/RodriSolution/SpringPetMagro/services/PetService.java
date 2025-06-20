package com.RodriSolution.SpringPetMagro.services;

import com.RodriSolution.SpringPetMagro.domain.PetValidator;
import com.RodriSolution.SpringPetMagro.dtos.PetRecordDto;
import com.RodriSolution.SpringPetMagro.exceptions.RecursoNaoEncontrado;
import com.RodriSolution.SpringPetMagro.model.Pet;
import com.RodriSolution.SpringPetMagro.model.Sexo;
import com.RodriSolution.SpringPetMagro.model.Tipo;
import com.RodriSolution.SpringPetMagro.repositories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PetService {
    private final PetRepository petRepository;
    private final PetValidator petValidator;

    public void salvarPet(Pet pet) {
        petValidator.validarDados(pet);
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

    public Pet findById(long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Pet com o ID " + id + " não encontrado"));


    }

    public void deletarPet(long id) {
        if(!petRepository.existsById(id)) {
            throw new RecursoNaoEncontrado("Pet com o ID " + id + " não encontrado");
        }
        petRepository.deleteById(id);
    }


}

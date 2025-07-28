package com.RodriSolution.SpringPetMagro.services;

import com.RodriSolution.SpringPetMagro.domain.PetValidator;
import com.RodriSolution.SpringPetMagro.model.dtos.PetResponsedDto;
import com.RodriSolution.SpringPetMagro.model.dtos.PetResquestDto;
import com.RodriSolution.SpringPetMagro.exceptions.RecursoNaoEncontrado;
import com.RodriSolution.SpringPetMagro.model.entities.Pet;
import com.RodriSolution.SpringPetMagro.model.enums.Sexo;
import com.RodriSolution.SpringPetMagro.model.enums.Tipo;
import com.RodriSolution.SpringPetMagro.model.entities.Tutor;
import com.RodriSolution.SpringPetMagro.repositories.PetRepository;
import com.RodriSolution.SpringPetMagro.repositories.TutorRepository;
import com.RodriSolution.SpringPetMagro.utils.PetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PetService {
    @Autowired
    private final PetRepository petRepository;
    @Autowired
    private final PetValidator petValidator;
    @Autowired
    private final TutorRepository tutorRepository;

    @Transactional
    public PetResponsedDto savePet(PetResquestDto petDto) {
        Tutor tutor = tutorRepository.findById(petDto.tutor())
                .orElseThrow(() -> new RecursoNaoEncontrado("Tutor não encontrado"));
        Pet pet = PetMapper.toEntity(petDto, tutor);
        petValidator.validarDados(pet);
        Pet saved = petRepository.save(pet);
        return PetMapper.toDto(saved);
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
   @Transactional
    public Pet findById(long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Pet com o ID " + id + " não encontrado"));


    }
    @Transactional
    public void deletarPet(long id) {
        if(!petRepository.existsById(id)) {
            throw new RecursoNaoEncontrado("Pet com o ID " + id + " não encontrado");
        }
        petRepository.deleteById(id);
    }

    public PetResponsedDto updatePet(Long id,PetResquestDto petDto) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(()-> new RecursoNaoEncontrado("Pet com ID" + id + "não encontrado"));

        Tutor tutor = tutorRepository.findById(petDto.tutor())
                .orElseThrow(()-> new RecursoNaoEncontrado("Tutor não encontrado"));

        pet.setTipo(petDto.tipo());
        pet.setPetNome(petDto.petNome());
        pet.setLastnamePet(petDto.lastnamePet());
        pet.setSexo(petDto.sexo());
        pet.setEndereco(petDto.endereco());
        pet.setIdade(petDto.idade());
        pet.setPeso(petDto.peso());
        pet.setRaca(petDto.raca());
        pet.setTutor(tutor);
        petValidator.validarDados(pet);
        Pet petSave = petRepository.save(pet);
        return PetMapper.toDto(petSave);
    }
}

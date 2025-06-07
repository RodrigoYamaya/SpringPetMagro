package com.RodriSolution.SpringPetMagro.repositories;

import com.RodriSolution.SpringPetMagro.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByPetNomeIgnoreCase(String petNome);
    List<Pet> findByRacaIgnoreCase(String raca);
    List<Pet> findByIdade(Double idade);
}

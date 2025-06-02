package com.RodriSolution.SpringPetMagro.repositories;

import com.RodriSolution.SpringPetMagro.model.Pet;
import com.RodriSolution.SpringPetMagro.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByPetNomeAndRacaAndIdade(String petNome, String raca, Double idade);

}

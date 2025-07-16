package com.RodriSolution.SpringPetMagro.repositories;

import com.RodriSolution.SpringPetMagro.model.entities.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    Boolean existsByNomeIgnoreCase(String nome);

}

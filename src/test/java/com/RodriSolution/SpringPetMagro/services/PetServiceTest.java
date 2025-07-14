package com.RodriSolution.SpringPetMagro.services;

import com.RodriSolution.SpringPetMagro.domain.PetValidator;
import com.RodriSolution.SpringPetMagro.exceptions.*;
import com.RodriSolution.SpringPetMagro.model.dtos.PetResquestDto;
import com.RodriSolution.SpringPetMagro.model.entities.Endereco;
import com.RodriSolution.SpringPetMagro.model.entities.Pet;
import com.RodriSolution.SpringPetMagro.model.entities.Tutor;
import com.RodriSolution.SpringPetMagro.model.enums.Sexo;
import com.RodriSolution.SpringPetMagro.model.enums.Tipo;
import com.RodriSolution.SpringPetMagro.repositories.PetRepository;
import com.RodriSolution.SpringPetMagro.repositories.TutorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @InjectMocks
    private PetService petService;
    @Mock
    private PetRepository petRepository;
    @Mock
    private Pet pet;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private Tutor tutor;

    @Test
    @DisplayName("Deve lançar exceção se nome ou sobrenome do pet forem inválidos")
    void deveLancarExcecaoSeNomeOuSobrenomeInvalido() {
        Tutor tutor = new Tutor();
        tutor.setId(1L);
        tutor.setEmail("rod@gmail.com");
        tutor.setCelular("4002-8922");
        when(tutorRepository.findById(1L)).thenReturn(Optional.of(tutor));

        PetResquestDto petResquestDto1 = new PetResquestDto(Tipo.GATO, "@@@@", "@@@", Sexo.FEMEA, new Endereco("rio", "53", "5"), 4.0, 4.0, "indefida ", 1L, LocalDate.parse("2010-05-30"));

        Assertions.assertThrows(NomeInvalidoException.class, () -> petService.savePet(petResquestDto1));

    }


    @Test
    @DisplayName("Deve lançar exceção se peso do pet for inválido")
    void deveLancarExcecaoSePesoInvalido() {
        Tutor tutor = new Tutor();
        tutor.setId(1L);
        when(tutorRepository.findById(1L)).thenReturn(Optional.of(tutor));

        PetResquestDto petDto = new PetResquestDto(Tipo.CACHORRO, "Rex", "Dogão", Sexo.MACHO, new Endereco("Rua A", "10", "2"), 70.0, 5.0, "Pitbull", // peso inválido (acima de 60)
                1L, LocalDate.parse("2018-01-01"));

        Assertions.assertThrows(PesoInvalidaException.class, () -> petService.savePet(petDto));
    }

    @Test
    @DisplayName("Deve lançar exceção se raça do pet for inválida")
    void deveLancarExcecaoSeRacaInvalida() {
        Tutor tutor = new Tutor();
        tutor.setId(1L);
        when(tutorRepository.findById(1L)).thenReturn(Optional.of(tutor));

        PetResquestDto petDto = new PetResquestDto(
                Tipo.CACHORRO,
                "Toby",
                "Brabo",
                Sexo.MACHO,
                new Endereco("Rua C", "45", "4"),
                10.0, 4.0, "P!tbull", // raça inválida
                1L, LocalDate.parse("2016-09-10")
        );

        Assertions.assertThrows(RacaInvalidaException.class, () ->
                petService.savePet(petDto)
        );
    }


    @Test
    @DisplayName("Deve lançar exceção se número do endereço for inválido")
    void deveLancarExcecaoSeEnderecoNumeroInvalido() {
        Tutor tutor = new Tutor();
        tutor.setId(1L);
        when(tutorRepository.findById(1L)).thenReturn(Optional.of(tutor));

        PetResquestDto petDto = new PetResquestDto(
                Tipo.CACHORRO,
                "Bolt",
                "Correria",
                Sexo.MACHO,
                new Endereco("Av. Principal", "10A", "Ap"), // número inválido (tem letra)
                8.0, 3.0, "Golden",
                1L, LocalDate.parse("2020-02-02")
        );

        Assertions.assertThrows(EnderecoInvalidoException.class, () ->
                petService.savePet(petDto)
        );
    }


    @Test
    void retornarTodoPets() {
        Pet pet = new Pet();
        when(petRepository.findAll()).thenReturn(Collections.singletonList(pet));

        List<Pet> pets = petService.listarPets();

        assertNotNull(pets);
        assertEquals(1, pets.size());
        assertEquals(pet, pets.get(0));
    }

    @Test
    void buscaPorFiltro() {

    }

    @Test
    void findById() {
    }

    @Test
    void deletarPet() {
    }

    @Test
    void updatePet() {
    }
}



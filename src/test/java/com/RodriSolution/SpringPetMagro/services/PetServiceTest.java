package com.RodriSolution.SpringPetMagro.services;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


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


    @BeforeEach
    public void setUp() {
        Pet pet = new Pet(1L, Tipo.GATO, "@@@@", "@@@", Sexo.FEMEA, new Endereco("rio", "53", "5"), 4.0, 4.0, "indefida ", LocalDate.parse("2010-05-30"), tutor);
        lenient().when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        lenient().when(petRepository.findAll()).thenReturn(Collections.singletonList(pet));

    }
    @Test
    @DisplayName("Deve lançar exceção se nome ou sobrenome do pet forem inválidos")
    void deveLancarExcecaoSeNomeOuSobrenomeInvalido() {
        Tutor tutor = new Tutor();
        tutor.setId(1L);
        tutor.setEmail("rod@gmail.com");
        tutor.setCelular("4002-8922");
        when(tutorRepository.findById(1L)).thenReturn(Optional.of(tutor));

        PetResquestDto petResquestDto1 = new PetResquestDto(Tipo.GATO, "@@@@", "@@@", Sexo.FEMEA, new Endereco("rio", "53", "5"), 4.0, 4.0, "indefida ", 1L, LocalDate.parse("2010-05-30"));

        assertThrows(NomeInvalidoException.class, () -> petService.savePet(petResquestDto1));

    }


    @Test
    @DisplayName("Deve lançar exceção se peso do pet for inválido")
    void deveLancarExcecaoSePesoInvalido() {
        Tutor tutor = new Tutor();
        tutor.setId(1L);
        when(tutorRepository.findById(1L)).thenReturn(Optional.of(tutor));

        PetResquestDto petDto = new PetResquestDto(Tipo.CACHORRO, "Rex", "Dogão", Sexo.MACHO, new Endereco("Rua A", "10", "2"), 70.0, 5.0, "Pitbull", // peso inválido (acima de 60)
                1L, LocalDate.parse("2018-01-01"));

        assertThrows(PesoInvalidaException.class, () -> petService.savePet(petDto));
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
                10.0, 4.0, "P!tbull",
                1L, LocalDate.parse("2016-09-10")
        );

        assertThrows(RacaInvalidaException.class, () ->
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
                new Endereco("Av. Principal", "10A", "Ap"),
                8.0, 3.0, "Golden",
                1L, LocalDate.parse("2020-02-02")
        );

        assertThrows(EnderecoInvalidoException.class, () ->
                petService.savePet(petDto)
        );
    }


    @Test
    void retornarTodoPets() {
        List<Pet> pets = petService.listarPets();

        assertEquals(0, pets.size());

        verify(petRepository).findAll();
        verifyNoMoreInteractions(petRepository);

    }

    @Test
    void buscaPorFiltro() {

    }

    @Test
    void findById() {
        Pet petId = petService.findById(1L);
        assertNotNull(petId);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar pet inexistente")
    void deveLancarExcecaoAoDeletarPetInexistente() {
        Long petIdInexistente = 999L;
        assertThrows(RecursoNaoEncontrado.class, () ->
                petService.deletarPet(petIdInexistente));
    }


    @Test
    void updatePet() {

    }

}




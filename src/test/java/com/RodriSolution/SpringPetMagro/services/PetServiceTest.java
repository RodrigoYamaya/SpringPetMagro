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
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@Slf4j
@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @InjectMocks
    private PetService petService;

    @Mock
    private PetRepository petRepository;

    @Mock
    private TutorRepository tutorRepository;


    @Mock
    private PetValidator petValidator;

    private Tutor tutor;
    private PetResquestDto petDto;
    private Pet pet;
    private List<Pet> petsMock;
    private  List<Pet> petDados;


    @BeforeEach
    public void setUp() {
        tutor = new Tutor(1L, "Rafahel", "@rodrigo190@gmail.com", "2140028922");
        petDto = new PetResquestDto(
                Tipo.CACHORRO, "Toby", "Brabo", Sexo.MACHO,
                new Endereco("Rua C", "45", "4"),
                10.0, 4.0, "Pitbull", 1L, LocalDate.parse("2016-09-10")
        );
       lenient().when(tutorRepository.findById(1L)).thenReturn(Optional.of(tutor));




    }

    @Test
    @DisplayName("Deve lançar exceção se nome ou sobrenome do pet forem inválidos")
    void deveLancarExcecaoSeNomeOuSobrenomeInvalido() {

        petDto = new PetResquestDto(
                Tipo.GATO,
                "",
                "",
                Sexo.FEMEA,
                new Endereco("rio", "100", "1500"),
                4.0,
                4.0,
                "indefinida",
                1L,
                LocalDate.parse("2010-05-30")
        );


        doThrow(new NomeInvalidoException("Nome ou sobrenome inválido"))
                .when(petValidator).validarDados(any(Pet.class));

        assertThrows(NomeInvalidoException.class, () -> petService.savePet(petDto));

    }




    @Test
    @DisplayName("Deve lançar exceção se peso do pet for inválido")
    void deveLancarExcecaoSePesoInvalido() {
        PetResquestDto petDtoInvalido = new PetResquestDto(
                Tipo.CACHORRO,
                "Toby",
                "Brabo",
                Sexo.MACHO,
                new Endereco("Rua C", "45", "4"),
                10.0,
                1000.0,
                "P!tbull",
                1L,
                LocalDate.parse("2016-09-10")
        );
        doThrow(new PesoInvalidaException("Peso inválido"))
                .when(petValidator).validarDados(any(Pet.class));

        assertThrows(PesoInvalidaException.class, () -> petService.savePet(petDtoInvalido));
    }

    @Test
    @DisplayName("Deve lançar exceção se raça do pet for inválida")
    void deveLancarExcecaoSeRacaInvalida() {

        PetResquestDto petDto = new PetResquestDto(
                Tipo.CACHORRO,
                "Toby",
                "Brabo",
                Sexo.MACHO,
                new Endereco("Rua C", "45", "4"),
                10.0, 4.0, "P!tbull",
                1L, LocalDate.parse("2016-09-10")
        );
        doThrow(new RacaInvalidaException("raça invalida"))
                .when(petValidator).validarDados(any(Pet.class));

        assertThrows(RacaInvalidaException.class, () ->
                petService.savePet(petDto)
        );
    }


    @Test
    @DisplayName("Deve lançar exceção se número do endereço for inválido")
    void deveLancarExcecaoSeEnderecoNumeroInvalido() {

        PetResquestDto petDto = new PetResquestDto(
                Tipo.CACHORRO,
                "Bolt",
                "Correria",
                Sexo.MACHO,
                new Endereco("Av. Principal", "10A", "Ap"),
                8.0, 3.0, "Golden",
                1L, LocalDate.parse("2020-02-02")
        );

        doThrow(new EnderecoInvalidoException("endereço invalido"))
                .when(petValidator).validarDados(any(Pet.class));

        assertThrows(EnderecoInvalidoException.class, () ->
                petService.savePet(petDto)
        );
    }


    @Test
    @DisplayName("Deve filtrar pets por nome (case-insensitive)")
    void buscaPorFiltro_DeveRetornarPetsPorNome() {

        Pet pet1 = new Pet(3L,Tipo.CACHORRO, "Rex", "Bravo", Sexo.MACHO, new Endereco("rio de janeiro", "55", "55"), 5.0, 5.0, "indefinida",  LocalDate.parse("2016-09-10"), tutor);
        Pet pet2 = new Pet(4L,Tipo.CACHORRO, "luna", "doce", Sexo.FEMEA, new Endereco("rio de janeiro", "55", "55"), 7.0, 5.0, "SIAMES", LocalDate.parse("2016-09-10"),tutor);
        petsMock = List.of(pet1,pet2);

        when(petRepository.findAll()).thenReturn(petsMock);

        List<Pet> resultado = petService.buscaPorFiltro(null, "rex", null, null, null, null, null, null, null);
        assertEquals(1, resultado.size());
        assertEquals("Rex", resultado.get(0).getPetNome());
    }

    @Test
    void deveFiltrarPorTipoEPesoMinimo() {
        Pet petGatoGrande = new Pet(3L,Tipo.GATO, "spyda", "yamaya", Sexo.FEMEA, new Endereco("rio de janeiro", "55", "55"), 5.0, 15.0, "indefinida",  LocalDate.parse("2016-09-10"), tutor);
        Pet petGatoPequeno = new Pet(4L,Tipo.GATO, "sakura", "yamaya", Sexo.FEMEA, new Endereco("rio de janeiro", "55", "55"), 7.0, 5.0, "SIAMES", LocalDate.parse("2016-09-10"),tutor);

        petDados = List.of(petGatoGrande, petGatoPequeno);
        when(petRepository.findAll()).thenReturn(petDados);

        List<Pet> resultado = petService.buscaPorFiltro(null, null, null, Tipo.GATO, null, null, null, 15.0, null);
        assertEquals(1, resultado.size());

    }
    @Test
    void deveRetornarTodosPetsQuandoFiltrosSaoNulos() {

        Pet pet1 = new Pet(3L,Tipo.CACHORRO, "Rex", "Bravo", Sexo.MACHO, new Endereco("rio de janeiro", "55", "55"), 5.0, 5.0, "indefinida",  LocalDate.parse("2016-09-10"), tutor);
        Pet pet2 = new Pet(4L,Tipo.CACHORRO, "luna", "doce", Sexo.FEMEA, new Endereco("rio de janeiro", "55", "55"), 7.0, 5.0, "SIAMES", LocalDate.parse("2016-09-10"),tutor);

        when(petRepository.findAll()).thenReturn(List.of(pet1,pet2));
        List<Pet> resultado = petService.buscaPorFiltro(999L, null, null,null,null,null, null, null, null);
        assertTrue(resultado.isEmpty());
    }
    @Test
    void findById() {
        Tutor tutor = new Tutor(1L, "Rafahel", "rod123@hotmail.com", "40028922");
        Pet pet = new Pet(1L, Tipo.CACHORRO, "Bob", "Dog", Sexo.MACHO,
                new Endereco("Rua A", "50", "5"), 5.0, 10.0,
                "Vira-lata", LocalDate.parse("2020-01-01"), tutor);

        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));


        Pet petEncontrado = petService.findById(1L);


        assertNotNull(petEncontrado);
        assertEquals("Bob", petEncontrado.getPetNome());
        verify(petRepository).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar pet inexistente")
    void deveLancarExcecaoAoDeletarPetInexistente() {
        Long petIdInexistente = 999L;
        assertThrows(RecursoNaoEncontrado.class, () ->
                petService.deletarPet(petIdInexistente));
    }

}


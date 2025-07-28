package com.RodriSolution.SpringPetMagro.domain;

import com.RodriSolution.SpringPetMagro.exceptions.*;
import com.RodriSolution.SpringPetMagro.model.entities.Endereco;
import com.RodriSolution.SpringPetMagro.model.entities.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetValidator {
    private static final String NAO_INFORMADO = "NÃO INFORMADO";

    public void validarDados(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("O objeto Pet não pode ser nulo.");
        }

        validarNome(pet);
        validarPeso(pet);
        validarIdade(pet);
        validarRaca(pet);
        validarEndereco(pet);
    }

    private void validarNome(Pet pet) {
        if (pet.getPetNome() == null || pet.getPetNome().isBlank() ||
                pet.getLastnamePet() == null || pet.getLastnamePet().isBlank()) {
            throw new NomeInvalidoException("Nome e sobrenome do pet são obrigatórios.");
        }

        if (!pet.getPetNome().matches("^[\\p{L} ]+$") ||
                !pet.getLastnamePet().matches("^[\\p{L} ]+$")) {
            throw new NomeInvalidoException("Nome e sobrenome devem conter apenas letras e espaços.");
        }
    }

    private void validarPeso(Pet pet) {
        if (pet.getPeso() == null) {
            throw new PesoInvalidaException("O peso do pet é obrigatório.");
        }
        if (pet.getPeso() > 60 || pet.getPeso() < 0.5) {
            throw new PesoInvalidaException("O peso do pet deve estar entre 0.5kg e 60kg.");
        }
    }

    private void validarIdade(Pet pet) {
        if (pet.getIdade() == null) {
            throw new IdadeInvalidaException("A idade do pet é obrigatória.");
        }

        double idadeEmAnos = pet.getIdade();
        if (idadeEmAnos > 0 && idadeEmAnos < 1) {
        }
        if (idadeEmAnos > 20) {
            throw new IdadeInvalidaException("Pets com mais de 20 anos não são permitidos.");
        }
    }

    private void validarRaca(Pet pet) {
        String raca = pet.getRaca();
        if (raca == null || raca.isBlank()) {
            pet.setRaca(NAO_INFORMADO);
            return;
        }
        if (!raca.matches("^[\\p{L} ]+$") && !raca.equals(NAO_INFORMADO)) {
            throw new RacaInvalidaException("A raça deve conter apenas letras e espaços.");
        }
    }

    private void validarEndereco(Pet pet) {
        Endereco endereco = pet.getEndereco();
        if (endereco == null) {
            throw new EnderecoInvalidoException("O endereço é obrigatório.");
        }

        String numero = endereco.getNumero();
        if (numero == null || numero.isBlank()) {
            endereco.setNumero(NAO_INFORMADO);
        } else if (!numero.matches("^[0-9a-zA-Z\\s-]+$")) {
            throw new EnderecoInvalidoException("O número do endereço contém caracteres inválidos.");
        }
    }
}

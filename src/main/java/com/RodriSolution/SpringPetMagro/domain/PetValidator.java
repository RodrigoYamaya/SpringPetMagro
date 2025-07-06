package com.RodriSolution.SpringPetMagro.domain;

import com.RodriSolution.SpringPetMagro.exceptions.*;
import com.RodriSolution.SpringPetMagro.model.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetValidator {
    public static void validarDados(Pet pet) {
       final String NAO_INFORMADO = "NÃO INFORMADO";


        if (pet.getPetNome() == null || pet.getPetNome().isBlank() || pet.getLastnamePet() == null || pet.getLastnamePet().isBlank()) {
            throw new NomeInvalidoException("Nome e sobrenome do pet são obrigatórios.");
        }
        if (!pet.getPetNome().matches("^[a-zA-Z]+$") || !pet.getLastnamePet().matches("^[a-zA-Z]+$")) {
            throw new NomeInvalidoException("Nome e sobrenome devem conter apenas letras de A-Z, sem caracteres especiais ou números.");
        }

        if (pet.getPeso() == null) {
            throw new PesoInvalidaException("Peso do pet é obrigatório.");
        }
        if (pet.getPeso() > 60 || pet.getPeso() < 0.5) {
            throw new PesoInvalidaException("O peso do pet deve estar entre 0.5kg e 60kg.");
        }

        if (pet.getIdade() == null) {
            throw new IdadeInvalidaException("Idade do pet é obrigatória.");
        }
        double idade = pet.getIdade();
        if (idade < 1.0) {
            idade = idade / 12.0;
        }
        if (idade > 20) {
            throw new IdadeInvalidaException("Pets com mais de 20 anos não são permitidos");
        }

        String raca = pet.getRaca();
        if (raca == null || raca.isBlank())  {
            raca = NAO_INFORMADO;
        }
        if (!raca.matches("^[a-zA-Z ]+$") && !raca.equals(NAO_INFORMADO)) {
            throw new RacaInvalidaException("Raça inválida: use apenas letras e espaços.");
        }

        if (pet.getEndereco() != null) {
            String numero = pet.getEndereco().getNumero();

            if  (numero != null && !numero.isBlank() && !numero.matches("^[0-9]+$")) {
                throw new EnderecoInvalidoException("endereço invalido: use apenas letras e espaços");
            }
            if (numero == null || numero.isBlank()) {
                pet.getEndereco().setNumero(NAO_INFORMADO);
            }
        }
    }
}

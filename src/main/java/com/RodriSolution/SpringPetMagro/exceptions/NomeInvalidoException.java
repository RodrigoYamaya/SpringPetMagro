package com.RodriSolution.SpringPetMagro.exceptions;

public class NomeInvalidoException extends IllegalArgumentException {
    public NomeInvalidoException(String message) {
        super(message);
    }
}

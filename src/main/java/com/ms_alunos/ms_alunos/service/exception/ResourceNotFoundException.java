package com.ms_alunos.ms_alunos.service.exception;

public class ResourceNotFoundException extends RuntimeException{

    // RuntimeException não precisa de try-catch
    // Exception customizada para quando não encontrar o recurso
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

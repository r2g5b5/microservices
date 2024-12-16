package com.example.microservices.inventoryservice.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@Setter
public class InvalidInputException extends RuntimeException {
    private final List<FieldError> errors;

    public InvalidInputException(List<FieldError> errors) {
        this.errors = errors;
    }
}

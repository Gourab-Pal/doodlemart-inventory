package com.doodlemart.inventory.stock.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InventoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleInventoryNotFound(InventoryNotFoundException exception) {
        return Map.of(
                "message", "Inventory record not found",
                "exception", exception.getMessage(),
                "timestamp", OffsetDateTime.now()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleIllegalArgument(IllegalArgumentException exception) {
        return Map.of(
                "message", "Invalid parameter parsed in the request body",
                "exception", exception.getMessage(),
                "timestamp", OffsetDateTime.now()
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleIllegalState(IllegalStateException exception) {
        return Map.of(
                "message", "This inventory operation is not allowed",
                "exception", exception.getMessage(),
                "timestamp", OffsetDateTime.now()
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        return Map.of(
                "message", "Inventory already exists for this product",
                "exception", exception.getMessage(),
                "timestamp", OffsetDateTime.now()
        );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleProductNotFound(ProductNotFoundException exception) {
        return Map.of(
                "message", "Unknown product received from upstream",
                "exception", exception.getMessage(),
                "timestamp", OffsetDateTime.now()
        );
    }
}

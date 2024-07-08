package com.j0k3r.andreanamaste.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalHandlers {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var fieldErrors = ex.getBindingResult().getFieldErrors();
        return ResponseEntity.badRequest().body(
                fieldErrors.stream().map(fieldError -> Map.of(
                        "field", fieldError.getField(),
                        "message", Objects.requireNonNull(fieldError.getDefaultMessage())
                )).toList()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleDefaultHandlerExceptionResolver(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(
                Map.of(
                        "message", "Body is required"
                )
        );
    }

}

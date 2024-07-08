package com.j0k3r.andreanamaste.handlers;

import com.j0k3r.andreanamaste.exceptions.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class MyHandlers {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> userHandlerException(UserException ex){
        return ResponseEntity.status(ex.getStatus()).body(
                Map.of(
                        "message", ex.getMessage(),
                        "status", ex.getStatus(),
                        "error", "Bad Request")
        );
    }

}

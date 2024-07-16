package com.j0k3r.andreanamaste.exceptions;

import lombok.Getter;

@Getter
public class ProductException extends Exception{
    private final int status;

    public ProductException(String message, int status) {
        super(message);
        this.status = status;
    }
}

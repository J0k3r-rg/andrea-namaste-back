package com.j0k3r.andreanamaste.exceptions;

import lombok.Getter;

@Getter
public class ProductException extends Exception{
    private final int code;

    public ProductException(String message, int code) {
        super(message);
        this.code = code;
    }
}

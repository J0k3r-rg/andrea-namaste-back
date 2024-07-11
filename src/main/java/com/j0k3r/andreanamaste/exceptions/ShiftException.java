package com.j0k3r.andreanamaste.exceptions;

import lombok.Getter;

@Getter
public class ShiftException extends Exception{

    private final int code;

    public ShiftException(String message, int code){
        super(message);
        this.code = code;
    }

}

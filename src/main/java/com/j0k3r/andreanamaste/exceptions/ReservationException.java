package com.j0k3r.andreanamaste.exceptions;

import lombok.Getter;

@Getter
public class ReservationException extends Exception{

    private final int code;

    public ReservationException(String message, int code){
        super(message);
        this.code = code;
    }

}

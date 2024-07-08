package com.j0k3r.andreanamaste.exceptions;

import lombok.Getter;

@Getter
public class UserException extends Exception{

    private final int status;

    public UserException(String message, int status) {
        super(message);
        this.status = status;
    }
}

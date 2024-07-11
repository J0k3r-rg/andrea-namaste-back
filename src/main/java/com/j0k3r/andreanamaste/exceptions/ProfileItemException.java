package com.j0k3r.andreanamaste.exceptions;

import lombok.Getter;

@Getter
public class ProfileItemException extends Exception {
    private int status;

    public ProfileItemException(String message, int status) {
        super(message);
        this.status = status;
    }

}

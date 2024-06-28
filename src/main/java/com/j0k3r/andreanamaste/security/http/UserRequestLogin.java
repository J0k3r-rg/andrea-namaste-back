package com.j0k3r.andreanamaste.security.http;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserRequestLogin {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}

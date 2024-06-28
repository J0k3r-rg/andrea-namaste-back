package com.j0k3r.andreanamaste.http.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    private String username;

    private String password;

    private String email;

    private String names;

    private String lastnames;

    private String phone;

}

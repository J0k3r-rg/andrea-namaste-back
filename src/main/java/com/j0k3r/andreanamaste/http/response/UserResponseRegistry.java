package com.j0k3r.andreanamaste.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseRegistry {

    private String id;
    private String username;
    private String email;
    private String names;
    private String lastnames;

}

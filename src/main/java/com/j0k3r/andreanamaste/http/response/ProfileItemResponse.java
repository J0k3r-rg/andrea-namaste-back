package com.j0k3r.andreanamaste.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileItemResponse {
    private String id;

    private String name;

    private String body;
}

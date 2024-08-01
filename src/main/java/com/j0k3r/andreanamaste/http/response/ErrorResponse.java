package com.j0k3r.andreanamaste.http.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private Integer status;

    private String message;
}

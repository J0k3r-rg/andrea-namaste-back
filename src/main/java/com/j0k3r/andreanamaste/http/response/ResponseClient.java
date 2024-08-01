package com.j0k3r.andreanamaste.http.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseClient <T>{

    private List<T> content;

    private ErrorResponse error;

}

package com.j0k3r.andreanamaste.http.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ShiftCreateRange {

    @NotNull
    private LocalDate date;

    private int start;

    private int end;

}

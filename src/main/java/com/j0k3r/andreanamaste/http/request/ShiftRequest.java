package com.j0k3r.andreanamaste.http.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShiftRequest {

    private LocalDate date;

    private LocalTime hour;

    private String idProduct;

}
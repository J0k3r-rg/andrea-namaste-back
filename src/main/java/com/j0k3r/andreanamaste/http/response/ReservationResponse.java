package com.j0k3r.andreanamaste.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {

    private String id;

    private ShiftResponse shift;

    private ProductResponse product;

    private String urlMeet;

    private Boolean isPaid;

}

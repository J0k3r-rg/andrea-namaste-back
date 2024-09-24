package com.j0k3r.andreanamaste.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private String id;

    private String name;

    private String description;

    private String imageUrl;

    private Double priceARS;

    private Double priceUSD;

    private LocalDateTime createdAt;

    private Boolean enabled;
}

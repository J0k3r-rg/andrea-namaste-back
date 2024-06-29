package com.j0k3r.andreanamaste.http.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    private String name;

    private String description;

    private String imageUrl;

    private Double priceARS;

    private Double priceUSD;

}

package com.j0k3r.andreanamaste.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @UuidGenerator
    private String id;

    private String name;

    private String description;

    private String imageUrl;

    private Double priceARS;

    private Double priceUSD;

    private Boolean enabled;

    public void disable(Product product) {
        product.enabled = false;
    }

    public void enable(Product product) {
        product.enabled = true;
    }
}

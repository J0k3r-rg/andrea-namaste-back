package com.j0k3r.andreanamaste.utils;

import com.j0k3r.andreanamaste.http.request.ProductRequest;
import com.j0k3r.andreanamaste.http.response.ProductResponse;
import com.j0k3r.andreanamaste.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductUtils {

    public static ProductResponse toProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .priceARS(product.getPriceARS())
                .priceUSD(product.getPriceUSD())
                .build();
    }

    public static Product toProduct(ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .imageUrl(productRequest.getImageUrl())
                .priceARS(productRequest.getPriceARS())
                .priceUSD(productRequest.getPriceUSD())
                .build();
    }

}

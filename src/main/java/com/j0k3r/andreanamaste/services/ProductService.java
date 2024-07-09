package com.j0k3r.andreanamaste.services;

import com.j0k3r.andreanamaste.http.request.ProductRequest;
import com.j0k3r.andreanamaste.http.response.ProductResponse;
import com.j0k3r.andreanamaste.models.Product;
import com.j0k3r.andreanamaste.repositories.ProductRepository;
import com.j0k3r.andreanamaste.utils.ProductUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = ProductUtils.toProduct(productRequest);
        productRepository.save(product);
        return ProductUtils.toProductResponse(product);
    }

    public ProductResponse getProductById(String id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found with id: " + id)
        );
        return ProductUtils.toProductResponse(product);
    }

    public Page<ProductResponse> getAllProducts(Pageable pageable){
        return productRepository.findAll(pageable).map(ProductUtils::toProductResponse);
    }

    @Transactional
    public ProductResponse updateProduct(String id, ProductRequest productRequest){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found with id: " + id)
        );
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setImageUrl(productRequest.getImageUrl());
        product.setPriceARS(productRequest.getPriceARS());
        product.setPriceUSD(productRequest.getPriceUSD());
        return ProductUtils.toProductResponse(product);
    }

    @Transactional
    public void disableProduct(String id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found with id: " + id)
        );
        productRepository.findById(id).ifPresent(product::disable);
    }

    @Transactional
    public void enableProduct(String id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found with id: " + id)
        );
        productRepository.findById(id).ifPresent(product::enable);
    }

    public Page<Product> getAllProductsClient(Pageable pageable) {
        return productRepository.findByEnabled(true, pageable);
    }
}

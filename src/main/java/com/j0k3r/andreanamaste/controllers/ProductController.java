package com.j0k3r.andreanamaste.controllers;

import com.j0k3r.andreanamaste.http.request.ProductRequest;
import com.j0k3r.andreanamaste.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequest productRequest){
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody @Valid ProductRequest productRequest){
        return ResponseEntity.ok(productService.updateProduct(id, productRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(@PageableDefault(size = 6, sort = {"createdAt"}) Pageable pageable){
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> disableProduct(@PathVariable String id){
        productService.disableProduct(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/enable")
    public ResponseEntity<?> enableProduct(@PathVariable String id){
        productService.enableProduct(id);
        return ResponseEntity.ok().build();
    }

}

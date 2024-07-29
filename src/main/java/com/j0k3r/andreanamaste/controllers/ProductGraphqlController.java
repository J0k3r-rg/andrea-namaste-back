package com.j0k3r.andreanamaste.controllers;

import com.j0k3r.andreanamaste.exceptions.ProductException;
import com.j0k3r.andreanamaste.http.response.ProductResponse;
import com.j0k3r.andreanamaste.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class ProductGraphqlController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @QueryMapping
    public ProductResponse getProductById(@Argument String id) throws ProductException {
        return productService.getProductById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @QueryMapping
    public Page<ProductResponse> getAllProducts(
            @Argument Integer size,
            @Argument String sort,
            @Argument Integer page,
            @Argument String direction
    ){
        Pageable pageable = PageRequest.of(
                page == null ? 1 : page,
                size == null ? 10 : size,
                Sort.by(Sort.Direction.fromString(direction != null ? direction : "ASC"),
                        sort != null && !sort.isEmpty() ? sort : "createdAt"));
        return productService.getAllProducts(pageable);
    }

}
package com.j0k3r.andreanamaste.controllers;

import com.j0k3r.andreanamaste.exceptions.ProductException;
import com.j0k3r.andreanamaste.http.response.ProductResponse;
import com.j0k3r.andreanamaste.services.ProductService;
import com.j0k3r.andreanamaste.utils.PageableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

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
            @Argument List<String> sorts,
            @Argument Integer page,
            @Argument String direction
    ){
        Pageable pageable = PageableUtils.createPageable(
                page,size,direction,sorts,"createdAt"
        );
        return productService.getAllProducts(pageable);
    }

}
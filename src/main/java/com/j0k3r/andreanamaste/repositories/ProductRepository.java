package com.j0k3r.andreanamaste.repositories;

import com.j0k3r.andreanamaste.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}

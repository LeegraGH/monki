package com.example.monki.repositories;

import com.example.monki.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByTitle(String title);
}

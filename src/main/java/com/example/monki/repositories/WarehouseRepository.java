package com.example.monki.repositories;

import com.example.monki.models.Product;
import com.example.monki.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Warehouse getByProduct(Product product);
}

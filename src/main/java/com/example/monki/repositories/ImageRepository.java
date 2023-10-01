package com.example.monki.repositories;

import com.example.monki.models.Image;
import com.example.monki.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}

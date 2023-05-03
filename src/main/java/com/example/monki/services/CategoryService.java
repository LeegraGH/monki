package com.example.monki.services;

import com.example.monki.models.Category;
import com.example.monki.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    public void saveCategory(Category category) {
        if (!categoryRepository.existsByName(category.getName())) {
            log.info("Saving new {}", category);
            categoryRepository.save(category);
        }
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}

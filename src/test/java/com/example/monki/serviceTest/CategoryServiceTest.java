package com.example.monki.serviceTest;

import com.example.monki.models.Category;
import com.example.monki.repositories.CategoryRepository;
import com.example.monki.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    void listCategories() {
        // given
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Electronics"));
        categoryList.add(new Category("Home & Garden"));
        when(categoryRepository.findAll()).thenReturn(categoryList);

        // when
        List<Category> result = categoryService.listCategories();

        // then
        assertEquals(2, result.size());
        assertEquals("Electronics", result.get(0).getName());
        assertEquals("Home & Garden", result.get(1).getName());
    }

    @Test
    void saveCategory() {
        // given
        Category category = new Category("Electronics");

        // when
        categoryService.saveCategory(category);

        // then
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void saveCategoryNotDuplicate() {
        // given
        Category category = new Category("Electronics");
        when(categoryRepository.existsByName(category.getName())).thenReturn(true);

        // when
        categoryService.saveCategory(category);

        // then
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    void deleteCategory() {
        // given
        Long id = 1L;

        // when
        categoryService.deleteCategory(id);

        // then
        verify(categoryRepository, times(1)).deleteById(id);
    }
}
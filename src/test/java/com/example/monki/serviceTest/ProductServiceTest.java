package com.example.monki.serviceTest;

import com.example.monki.models.Product;
import com.example.monki.repositories.ImageRepository;
import com.example.monki.repositories.ProductRepository;
import com.example.monki.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ImageRepository imageRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository, imageRepository);
    }

    @Test
    void listProducts() {
        // given
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Product 1", "Description 1", 10,10));
        productList.add(new Product("Product 2", "Description 2", 20,20));
        when(productRepository.findAll()).thenReturn(productList);

        // when
        List<Product> result = productService.listProducts();

        // then
        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getTitle());
        assertEquals("Description 2", result.get(1).getDescription());
        assertEquals(20.0, result.get(1).getPrice());
    }

    @Test
    void saveProduct() throws IOException {
        // given
        Product product = new Product("New Product", "New Description", 30,30);
        MockMultipartFile file = new MockMultipartFile("image", "image.jpg",
                "image/jpeg", "test image".getBytes());
        when(productRepository.existsByTitle(product.getTitle())).thenReturn(false);

        // when
        productService.saveProduct(product, file);

        // then
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void deleteProduct() {
        // given
        Long id = 1L;

        // when
        productService.deleteProduct(id);

        // then
        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    void getProductById() {
        // given
        Long id = 1L;
        Product product = new Product("Product 1", "Description 1", 10,10);
        Optional<Product> optionalProduct = Optional.of(product);
        when(productRepository.findById(id)).thenReturn(optionalProduct);

        // when
        Product result = productService.getProductById(id);

        // then
        assertEquals("Product 1", result.getTitle());
        assertEquals("Description 1", result.getDescription());
        assertEquals(10.0, result.getPrice());
    }
}
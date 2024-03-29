package com.example.monki.services;

import com.example.monki.models.Image;
import com.example.monki.models.Product;
import com.example.monki.models.Warehouse;
import com.example.monki.repositories.ImageRepository;
import com.example.monki.repositories.ProductRepository;
import com.example.monki.repositories.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final WarehouseRepository warehouseRepository;

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void saveProduct(Product product, MultipartFile file) throws IOException {
        if (!productRepository.existsByTitle(product.getTitle())){
            Image image;
            if (file.getSize() != 0) {
                image = toImageEntity(file);
                product.addImage(image);
            }
            log.info("Saving new product '{}'", product.getTitle());
            productRepository.save(product);
            warehouseRepository.save(new Warehouse(product, 0));
        }
    }

    public void changeProduct(Long id, Product product, MultipartFile file) throws IOException {
        Product updateProduct = productRepository.findById(id).orElse(null);
        Image image;
        if (file.getSize() != 0) {
            assert updateProduct != null;
            imageRepository.deleteById(updateProduct.getImage().getId());
            image = toImageEntity(file);
            updateProduct.addImage(image);
        }
        assert updateProduct != null;
        updateProduct.setPrice(product.getPrice());
        updateProduct.setCategory(product.getCategory());
        updateProduct.setTitle(product.getTitle());
        updateProduct.setDescription(product.getDescription());
        updateProduct.setWeight(product.getWeight());
        log.info("Updating product '{}'", updateProduct.getTitle());
        productRepository.save(updateProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}

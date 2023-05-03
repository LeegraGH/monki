package com.example.monki.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@NoArgsConstructor
//@Embeddable
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Basket {
//    @ElementCollection
    private List<Product> products = new ArrayList<>();
    private int amount = 0;
    private int quantity = 0;

    public void addProduct(Product product) {
        products.add(product);
        quantity+=1;
        amount+=product.getPrice();
    }

    public void deleteProduct(Long id) {
        for (Product product : products) {
            if (Objects.equals(product.getId(), id)) {
                amount -= product.getPrice();
                quantity-=1;
                products.remove(product);
                break;
            }
        }
    }

}

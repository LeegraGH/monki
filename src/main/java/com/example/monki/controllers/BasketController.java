package com.example.monki.controllers;

import com.example.monki.models.Basket;
import com.example.monki.models.Order;
import com.example.monki.models.Product;
import com.example.monki.services.OrderService;
import com.example.monki.services.ProductService;
import com.example.monki.services.WarehouseService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@SessionAttributes("basket")
public class BasketController {
    private static final String BASKET_SESSION_KEY = "basketProducts";
    private final ProductService productService;
    private final OrderService orderService;
    private final WarehouseService warehouseService;

    @GetMapping("/basket")
    public String showBasket(Model model, HttpSession session) {
        Basket basket = getBasket(session);
        model.addAttribute("basket", basket);
        model.addAttribute("orderForm", new Order());
        return "basket";
    }

    @PostMapping("/basket/order")
    public String order(@ModelAttribute("orderForm") Order order,
                        @ModelAttribute("basket") Basket basket,
                        HttpSession session) {
        order.setInformation(new ArrayList<>());
        order.setAmount(basket.getAmount());
        for (int i = 0; i < basket.getProducts().size(); i++){
            Product product = (Product)basket.getProducts().get(i).get(0);
            order.getInformation().add(product.getTitle());
        }
        orderService.saveOrder(order);
        session.invalidate();
        return "redirect:/order/complete";
    }

    @PostMapping("/basket/add/{id}")
    public String addProduct(@PathVariable Long id, HttpSession session) {
        Basket basket = getBasket(session);
        Product product = productService.getProductById(id);
        int count = warehouseService.getCountOfProduct(product);
        if (count > 0){
            basket.addProduct(product);
            warehouseService.reduceProduct(product);
            saveBasket(basket, session);
        }
        return "redirect:/products";
    }

    @PostMapping("/basket/delete/{id}")
    public String deleteProduct(@PathVariable Long id, HttpSession session) {
        Basket basket = getBasket(session);

        Product product = productService.getProductById(id);
        int count = warehouseService.getCountOfProduct(product);
        int cntBasket = basket.getQuantity();
        basket.deleteProduct(id);
        if (cntBasket!=basket.getQuantity()) {
            warehouseService.increaseProduct(product);
        }
        saveBasket(basket, session);
        return "redirect:/basket";
    }

    @PostMapping("/basket/more/{id}")
    public String moreProduct(@PathVariable Long id, HttpSession session) {
        Basket basket = getBasket(session);
        Product product = productService.getProductById(id);
        int count = warehouseService.getCountOfProduct(product);
        if (count > 0){
            basket.oneMoreProduct(id);
            warehouseService.reduceProduct(product);
            saveBasket(basket, session);
        }
        return "redirect:/basket";
    }

    @PostMapping("/basket/less/{id}")
    public String lessProduct(@PathVariable Long id, HttpSession session) {
        Basket basket = getBasket(session);
        Product product = productService.getProductById(id);
        int count = warehouseService.getCountOfProduct(product);
        int cntBasket = basket.getQuantity();
        basket.oneLessProduct(id);
        if (cntBasket!=basket.getQuantity()) {
            warehouseService.increaseProduct(product);
        }
        saveBasket(basket, session);
        return "redirect:/basket";
    }

    @PostMapping("/basket/delete")
    public String deleteAllProducts(HttpSession session) {
        Basket basket = getBasket(session);
        basket.deleteAllProducts();
        saveBasket(basket, session);
        return "redirect:/basket";
    }

    private Basket getBasket(HttpSession session) {
        Basket basket = (Basket) session.getAttribute(BASKET_SESSION_KEY);
        if (basket == null) {
            basket = new Basket();
            session.setAttribute(BASKET_SESSION_KEY, basket);
        }
        return basket;
    }

    private void saveBasket(Basket basket, HttpSession session) {
        session.setAttribute(BASKET_SESSION_KEY, basket);
    }
}

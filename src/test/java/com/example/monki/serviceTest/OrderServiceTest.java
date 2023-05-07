package com.example.monki.serviceTest;

import com.example.monki.models.Order;
import com.example.monki.repositories.OrderRepository;
import com.example.monki.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderService(orderRepository);
    }

    @Test
    void listOrders() {
        // given
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("John Doe", "89005553535", "123 Main St New York", "", "card", 1001));
        orderList.add(new Order("Jane Smith", "89005553530", "456 Oak Ave Los Angeles", "", "cash", 2001));
        when(orderRepository.findAll()).thenReturn(orderList);

        // when
        List<Order> result = orderService.listOrders();

        // then
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("456 Oak Ave Los Angeles", result.get(1).getAddress());
        assertEquals(2001, result.get(1).getAmount());
    }

    @Test
    void saveOrder() {
        // given
        Order order = new Order("John Doe", "89005553535", "123 Main St New York", "", "card", 1001);

        // when
        orderService.saveOrder(order);

        // then
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void deleteOrder() {
        // given
        Long id = 1L;

        // when
        orderService.deleteOrder(id);

        // then
        verify(orderRepository, times(1)).deleteById(id);
    }

    @Test
    void getOrderById() {
        // given
        Long id = 1L;
        Order order = new Order("John Doe", "89005553535", "123 Main St New York", "", "card", 1001);
        Optional<Order> optionalOrder = Optional.of(order);
        when(orderRepository.findById(id)).thenReturn(optionalOrder);

        // when
        Order result = orderService.getOrderById(id);

        // then
        assertEquals("John Doe", result.getName());
        assertEquals("89005553535", result.getPhone());
        assertEquals("123 Main St New York", result.getAddress());
        assertEquals("", result.getComment());
        assertEquals(1001, result.getAmount());
    }
}
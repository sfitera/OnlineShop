package org.dreamteam.onlineshop.service;


import lombok.extern.slf4j.Slf4j;
import org.dreamteam.onlineshop.model.DTOs.OrderDTO;
import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.OrderItem;
import org.dreamteam.onlineshop.model.Product;
import org.dreamteam.onlineshop.model.User;
import org.dreamteam.onlineshop.model.enums.OrderStatus;
import org.dreamteam.onlineshop.repository.OrderItemRepository;
import org.dreamteam.onlineshop.repository.OrderRepository;
import org.dreamteam.onlineshop.repository.ProductRepository;
import org.dreamteam.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@Service
public class OrderServiceBean implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderServiceBean(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Používateľ neexistuje"));

        Order order = new Order(user, orderDTO.getOrderStatus() != null ? orderDTO.getOrderStatus() : OrderStatus.CREATED);
        order.setOrderDate(LocalDate.now());

        for (OrderDTO.OrderItemDto itemDto : orderDTO.getOrderItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Produkt neexistuje"));
            OrderItem orderItem = new OrderItem(product, itemDto.getQuantity());
            order.addOrderItem(orderItem);
        }

        return orderRepository.save(order);
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        if (orderStatus != null) {
            order.setOrderStatus(orderStatus);
            orderRepository.save(order);
        }
    }

    @Override
    public void deleteOrder(Long orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        orderRepository.delete(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

}
package org.dreamteam.onlineshop.service;


import lombok.extern.slf4j.Slf4j;
import org.dreamteam.onlineshop.mapper.EntityMapper;
import org.dreamteam.onlineshop.model.DTOs.OrderDTO;
import org.dreamteam.onlineshop.model.DTOs.OrderItemDTO;
import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.OrderItem;
import org.dreamteam.onlineshop.model.Product;
import org.dreamteam.onlineshop.model.User;
import org.dreamteam.onlineshop.model.enums.OrderStatus;
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
    private final EntityMapper entityMapper;

    @Autowired
    public OrderServiceBean(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, EntityMapper entityMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Používateľ neexistuje"));

        Order order = entityMapper.toOrderEntity(orderDTO);
        order.setUser(user);
        if (orderDTO.getOrderStatus() != null) {
            order.setOrderStatus(orderDTO.getOrderStatus());
        } else {
            order.setOrderStatus(OrderStatus.CREATED);
        }
        order.setOrderDate(LocalDate.now());

        for (OrderItemDTO itemDto : orderDTO.getOrderItems()) {
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
                .orElseThrow(() -> new IllegalArgumentException("Objednavka neexistuje"));
        if (orderStatus != null) {
            order.setOrderStatus(orderStatus);
            orderRepository.save(order);
        }
    }

    @Override
    public void deleteOrder(Long orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Objednavka neexistuje"));
        orderRepository.delete(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Objednavka neexistuje"));
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
package org.dreamteam.onlineshop.service;


import lombok.extern.slf4j.Slf4j;
import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.OrderItem;
import org.dreamteam.onlineshop.model.enums.OrderStatus;
import org.dreamteam.onlineshop.repository.OrderItemRepository;
import org.dreamteam.onlineshop.repository.OrderRepository;
import org.dreamteam.onlineshop.repository.ProductRepository;
import org.dreamteam.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderServiceBean implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceBean(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }

    @Override
    public void createOrder(Order order) {
        orderRepository.save(order);
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

    @Override
    public void addOrderItem(Long orderId, OrderItem orderItem) {
        Order order = getOrderById(orderId);
        order.getOrderItems().add(orderItem);
        orderRepository.save(order);
    }

    @Override
    public void deleteOrderItem(Long orderId, Long itemId) {
        Order order = getOrderById(orderId);
        order.getOrderItems().removeIf(item -> item.getId().equals(itemId));
        orderRepository.save(order);
    }

}
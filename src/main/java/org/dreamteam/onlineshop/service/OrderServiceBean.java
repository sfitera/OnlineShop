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
public class OrderServiceBean implements OrderService{

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceBean(UserRepository userRepository, OrderRepository orderRepository,OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;

    }

    @Override
    public Order addOrder(Long userId,List<OrderItem> orderItems) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        var order = new Order(user,OrderStatus.CREATED,orderItems);
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long orderId, OrderStatus orderStatus) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        if(orderStatus!= null){
            order.setOrderStatus(orderStatus);
        }
        return order;
    }

    @Override
    public void deleteOrder(Long orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        orderRepository.delete(order);
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}

package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.OrderItem;
import org.dreamteam.onlineshop.model.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    void createOrder(Order order);
    void updateOrderStatus(Long orderId, OrderStatus orderStatus);
    void deleteOrder(Long orderId);
    Order getOrderById(Long orderId);
    List<Order> getAllOrders();
    List<Order> getOrdersByUserId(Long userId);

    void addOrderItem(Long orderId, OrderItem orderItem);
    void deleteOrderItem(Long orderId, Long itemId);

}

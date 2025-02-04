package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.DTOs.OrderDTO;
import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    Order createOrder(OrderDTO orderDto);

    void updateOrderStatus(Long orderId, OrderStatus orderStatus);

    void deleteOrder(Long orderId);

    Order getOrderById(Long orderId);

    List<Order> getAllOrders();

    List<Order> getOrdersByUserId(Long userId);

}

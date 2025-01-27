package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.OrderItem;

import java.util.List;

public interface OrderService {

    Order addOrder(Order order);
    Order updateOrder(Long orderId);
    Order deleteOrder(Long orderId);
    Order getOrder(Long orderId);
    List<Order> getAllOrders();

    List<OrderItem> getAllOrderItems(Long orderId);
}

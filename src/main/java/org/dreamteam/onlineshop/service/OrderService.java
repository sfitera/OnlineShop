package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.OrderItem;
import org.dreamteam.onlineshop.model.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    Order addOrder(Long userId, List<OrderItem> orderItems);
    Order updateOrder(Long orderId, OrderStatus orderStatus);
    void deleteOrder(Long orderId);
    Order getOrder(Long orderId);
    List<Order> getAllOrders();

}

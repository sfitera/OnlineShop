package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.OrderItem;

import java.util.List;

public interface OrderItemService {

    OrderItem addOrderItem(OrderItem orderItem);
    OrderItem updateOrderItem(OrderItem orderItem);
    OrderItem deleteOrderItem(OrderItem orderItem);
    OrderItem getOrderItem(Long orderItemId);
    List<OrderItem> getAllOrderItems();

}

package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.DTOs.OrderItemDTO;
import org.dreamteam.onlineshop.model.OrderItem;


import java.util.List;

public interface OrderItemService {

    OrderItem addOrderItem(OrderItemDTO orderItemDTO);

    void updateOrderItem(Long orderItemId, int quantity);

    void deleteOrderItem(Long orderItemId);

    OrderItem getOrderItem(Long orderItemId);

    List<OrderItem> getAllOrderItems();

    void clearCart(Long userId);

    List<OrderItem> getCartItems();
}

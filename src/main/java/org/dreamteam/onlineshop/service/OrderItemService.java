package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.DTOs.OrderItemDTO;
import org.dreamteam.onlineshop.model.OrderItem;


import java.util.List;

public interface OrderItemService {

    void addOrderItem(Long orderId, OrderItemDTO orderItemDTO);

    void updateOrderItem(Long orderItemId, int quantity);

    void deleteOrderItem(Long orderId, Long orderItemId);

    OrderItem getOrderItem(Long orderItemId);

    List<OrderItem> getAllOrderItems();

}

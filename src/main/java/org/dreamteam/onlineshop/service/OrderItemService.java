package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.OrderItem;
import org.dreamteam.onlineshop.model.Product;

import java.util.List;

public interface OrderItemService {

    OrderItem addOrderItem(Order order, Product product, int quantity);
    OrderItem updateOrderItem(Long id,Order order, Product product,int quantity);
    void deleteOrderItem(Long orderItemId);
    OrderItem getOrderItem(Long orderItemId);
    List<OrderItem> getAllOrderItems();

}

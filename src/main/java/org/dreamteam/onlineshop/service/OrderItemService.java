package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.OrderItem;
import org.dreamteam.onlineshop.model.Product;

import java.util.List;

public interface OrderItemService {

    void addOrderItem(Product product, int quantity);
    void updateOrderItem(Long id, Order order, Product product, int quantity);
    void deleteOrderItem(Long orderItemId);
    OrderItem getOrderItem(Long orderItemId);
    List<OrderItem> getAllOrderItems();

}

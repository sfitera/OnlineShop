package org.dreamteam.onlineshop.service;

import lombok.extern.slf4j.Slf4j;
import org.dreamteam.onlineshop.mapper.EntityMapper;
import org.dreamteam.onlineshop.model.DTOs.OrderItemDTO;
import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.OrderItem;
import org.dreamteam.onlineshop.model.Product;
import org.dreamteam.onlineshop.repository.OrderItemRepository;
import org.dreamteam.onlineshop.repository.OrderRepository;
import org.dreamteam.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderItemServiceBean implements OrderItemService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final EntityMapper entityMapper;

    @Autowired
    public OrderItemServiceBean(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository, EntityMapper entityMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.entityMapper = entityMapper;
    }

    public void addOrderItem(OrderItemDTO orderItemDTO) {
        Product product = productRepository.findById(orderItemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        OrderItem newOrderItem = entityMapper.toOrderItemEntity(orderItemDTO);
        newOrderItem.setProduct(product);
        newOrderItem.setItemPrice(newOrderItem.getQuantity() * product.getProductPrice());

        orderItemRepository.save(newOrderItem);
    }

    @Override
    public void updateOrderItem(Long orderItemId, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));

        if (quantity == 0) {
            orderItemRepository.delete(orderItem);
        } else {
            orderItem.setQuantity(quantity);
            orderItem.setItemPrice(quantity * orderItem.getProduct().getProductPrice());
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public void deleteOrderItem(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));

        orderItemRepository.delete(orderItem);

    }

    @Override
    public OrderItem getOrderItem(Long orderItemId) {
        return orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new IllegalArgumentException("Order item not found"));
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }
}

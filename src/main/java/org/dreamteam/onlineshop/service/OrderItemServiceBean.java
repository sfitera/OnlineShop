package org.dreamteam.onlineshop.service;

import lombok.extern.slf4j.Slf4j;
import org.dreamteam.onlineshop.mapper.EntityMapper;
import org.dreamteam.onlineshop.model.DTOs.OrderItemDTO;
import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.OrderItem;
import org.dreamteam.onlineshop.model.Product;
import org.dreamteam.onlineshop.model.enums.OrderStatus;
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

    @Override
    public OrderItem addOrderItem(OrderItemDTO orderItemDTO) {
        Product product = productRepository.findById(orderItemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<OrderItem> existingItem = orderItemRepository.findByProductId(product.getId());
        OrderItem orderItem;

        if (existingItem.isPresent()) {
            orderItem = existingItem.get();
            orderItem.setQuantity(orderItemDTO.getQuantity()); // ✅ Oprava: Nekumulujeme staré množstvo
        } else {
            orderItem = entityMapper.toOrderItemEntity(orderItemDTO);
            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemDTO.getQuantity());
        }

        orderItem.setItemPrice(orderItem.getQuantity() * product.getProductPrice());
        orderItem = orderItemRepository.save(orderItem);

        orderItem.setProduct(product); // ✅ Zabezpečíme, že `product` nebude `null`
        return orderItem;
    }

    @Override
    public void updateOrderItem(Long orderItemId, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));
        log.info("🔄 Updating OrderItem ID: " + orderItemId + " New Quantity: " + quantity);

        if (quantity == 0) {
            orderItemRepository.delete(orderItem);
            log.info("🗑️ OrderItem deleted: " + orderItemId);
            return;
        } else {
            orderItem.setQuantity(quantity);
            orderItem.setItemPrice(quantity * orderItem.getProduct().getProductPrice());
            orderItemRepository.save(orderItem);
        }
    }


    @Override
    public void deleteOrderItem(Long orderItemId) {
        log.info("🗑️ Odstraňujem položku s ID: " + orderItemId);
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("❌ OrderItem not found: " + orderItemId));

        orderItemRepository.delete(orderItem);
        log.info("✅ Položka úspešne odstránená: " + orderItemId);
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

    public void clearCart(Long userId) {
        List<Order> activeOrders = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.CREATED);

        for (Order order : activeOrders) {
            orderItemRepository.deleteAll(order.getOrderItems());
        }
    }
    public List<OrderItem> getCartItems() {
        return orderItemRepository.findValidUnorderedItems();
    }
}

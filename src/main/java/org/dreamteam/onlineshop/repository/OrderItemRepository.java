package org.dreamteam.onlineshop.repository;

import org.dreamteam.onlineshop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
   // takto sa to neda :D
    // List<OrderItem> findByOrderId(Long orderId);
}

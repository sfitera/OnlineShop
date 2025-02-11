package org.dreamteam.onlineshop.repository;

import org.dreamteam.onlineshop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Optional<OrderItem> findByProductId(Long productId);

}

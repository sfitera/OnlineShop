package org.dreamteam.onlineshop.repository;

import org.dreamteam.onlineshop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Optional<OrderItem> findByProductId(Long productId);
    Optional<OrderItem> findById(Long id);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.order IS NULL")
    List<OrderItem> findUnorderedItems();

    @Query("SELECT oi FROM OrderItem oi WHERE oi.order IS NULL AND oi.product IS NOT NULL")
    List<OrderItem> findValidUnorderedItems();
}

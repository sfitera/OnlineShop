package org.dreamteam.onlineshop.repository;

import org.dreamteam.onlineshop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}

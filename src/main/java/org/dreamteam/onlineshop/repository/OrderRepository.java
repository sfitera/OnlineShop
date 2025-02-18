package org.dreamteam.onlineshop.repository;

import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
    List<Order> findAllByUserId(@Param("userId") Long userId);

    List<Order> findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);
}

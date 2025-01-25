package org.dreamteam.onlineshop.repository;

import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);
    List<Order> findByOrderStatus(OrderStatus orderStatus);
    List<Order> findByCustomerName(String name);
}

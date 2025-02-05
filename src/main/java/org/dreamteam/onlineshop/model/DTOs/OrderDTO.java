package org.dreamteam.onlineshop.model.DTOs;

import lombok.*;
import org.dreamteam.onlineshop.model.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {

    private Long userId;
    private List<OrderItemDTO> orderItems;
    private OrderStatus orderStatus;
    private LocalDate orderDate;
}

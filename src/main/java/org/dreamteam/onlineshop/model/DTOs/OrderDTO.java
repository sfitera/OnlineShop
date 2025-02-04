package org.dreamteam.onlineshop.model.DTOs;

import lombok.*;
import org.dreamteam.onlineshop.model.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {

    private Long userId;
    private List<OrderItemDto> orderItems;
    private OrderStatus orderStatus;
    private LocalDate orderDate;


    @Data
    public static class OrderItemDto {
        private Long productId;
        private int quantity;
    }

}

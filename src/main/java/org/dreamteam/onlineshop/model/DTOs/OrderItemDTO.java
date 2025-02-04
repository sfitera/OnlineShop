package org.dreamteam.onlineshop.model.DTOs;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long productId;
    private int quantity;
}

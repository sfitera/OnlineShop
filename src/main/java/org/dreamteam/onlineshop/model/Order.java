package org.dreamteam.onlineshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.dreamteam.onlineshop.model.enums.OrderStatus;

import java.time.LocalDate;

@Builder
@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    private Long id;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private double totalPrice;
    private LocalDate orderDate;
    private OrderStatus orderStatus;
    private OrderItem orderItem;


}

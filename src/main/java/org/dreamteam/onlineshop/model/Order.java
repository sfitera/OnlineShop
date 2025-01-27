package org.dreamteam.onlineshop.model;

import jakarta.persistence.*;
import lombok.*;
import org.dreamteam.onlineshop.model.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private double totalPrice;
    private LocalDate orderDate;
    private OrderStatus orderStatus;
    @OneToMany
    private List<OrderItem> orderItems;

}

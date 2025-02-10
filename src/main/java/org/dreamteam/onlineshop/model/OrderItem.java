package org.dreamteam.onlineshop.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderItems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    private int quantity;
    private double itemPrice;


    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.itemPrice = product.getProductPrice() * quantity;
    }
}



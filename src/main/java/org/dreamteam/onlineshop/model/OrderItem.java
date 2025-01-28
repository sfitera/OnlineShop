package org.dreamteam.onlineshop.model;


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
    @ManyToOne
    private Order order;
    private int quantity;
    private double itemPrice;


    public OrderItem(Order order,Product product, int quantity){
        this.order = order;
        this.product=product;
        this.quantity=quantity;
        this.itemPrice=setItemPrice(quantity,product);
    }

    private double setItemPrice(int quantity, Product product) {
        return quantity*product.getPrice();
    }
}



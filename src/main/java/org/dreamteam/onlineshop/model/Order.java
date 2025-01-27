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
    @ManyToOne
    private User user;
    private double totalPrice;
    private LocalDate orderDate;
    private OrderStatus orderStatus;
    @OneToMany
    private List<OrderItem> orderItems;

    public Order(User user, OrderStatus orderStatus, List<OrderItem> orderItems) {
        this.user = user;
        this.totalPrice = setTotalPrice(orderItems);
        this.orderDate = LocalDate.now();
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
    }

    private double setTotalPrice(List<OrderItem> orderItems) {
       double price =0;
        for (OrderItem orderItem : orderItems) {
            price += orderItem.getPrice();
        }
        return price;
    }
}

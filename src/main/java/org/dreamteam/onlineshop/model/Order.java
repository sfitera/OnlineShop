package org.dreamteam.onlineshop.model;

import jakarta.persistence.*;
import lombok.*;
import org.dreamteam.onlineshop.model.enums.OrderStatus;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private double totalPrice;
    private LocalDate orderDate;
    private OrderStatus orderStatus;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();


    public Order(User user, OrderStatus orderStatus) {
        this.user = user;
        this.orderDate = LocalDate.now();
        this.orderStatus = orderStatus;
    }

    public double setTotalPrice(OrderItem orderItem) {
        double price = 0;
        int quantity = orderItem.getQuantity();
        price = quantity * orderItem.getItemPrice();
        return price;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.totalPrice = setTotalPrice(orderItem);
        this.orderItems.add(orderItem);

    }

    public void recalculateTotalPrice() {
        this.totalPrice = orderItems.stream()
                .mapToDouble(OrderItem::getItemPrice)
                .sum();
    }

}




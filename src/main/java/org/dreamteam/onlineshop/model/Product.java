package org.dreamteam.onlineshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 250)
    private String name;
    @Column(nullable = false)
    private Double price;
    private int quantity;
    private Boolean availability;
    private String image;
    @ManyToMany
    private List<Category> categories;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false, length = 1000)
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    private OrderItem orderItem;

}


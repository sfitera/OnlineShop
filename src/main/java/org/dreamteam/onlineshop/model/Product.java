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
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String name;
    private Double price;
    private int quantity;
    private Boolean availability;
    private String image;
    @ManyToMany
    private List<Category> categories;
    private String author;
    @Column(length = 1000)
    private String description;
    @OneToOne (cascade = CascadeType.ALL)
    private OrderItem orderItem;

}



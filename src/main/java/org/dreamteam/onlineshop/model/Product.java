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
    private boolean availability;
    private String image;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Category category;
    private String author;
    @Column(nullable = false, length = 1000)
    private String description;

    public Product(String name, double price, int quantity, boolean availability, String image, Category category, String author, String description) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.availability = availability;
        this.image = image;
        this.category = category;
        this.author = author;
        this.description = description;
    }
}


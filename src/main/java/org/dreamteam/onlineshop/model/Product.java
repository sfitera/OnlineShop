package org.dreamteam.onlineshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String name;
    private Double price;
    private String image;
    private String category;
    private String author;
    private Boolean availability;
    @Column(length = 1000)
    private String description;
}



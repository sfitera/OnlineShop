package org.dreamteam.onlineshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    @Column(length = 100)
    private String name;
    private BigDecimal price;
    private String image;
    private String category;
    private String author;
    private Boolean availability;
    @Column(length = 1000)
    private String description;
}



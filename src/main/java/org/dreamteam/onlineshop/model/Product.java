package org.dreamteam.onlineshop.model;

import jakarta.persistence.*;
import lombok.*;
import org.dreamteam.onlineshop.model.enums.Category;

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
    private String productName;
    @Column(nullable = false)
    private Double productPrice;
    private int productQuantity;
    private boolean productAvailability;
    private String productImage;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Category productCategory;
    private String productAuthor;
    @Column(nullable = false, length = 1000)
    private String productDescription;

    public Product(String productName, double productPrice, int productQuantity, boolean productAvailability, String productImage, Category producCategory, String productAuthor, String productDescription) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productAvailability= productAvailability;
        this.productImage= productImage;
        this.productAuthor = productAuthor;
        this.productDescription = productDescription;
    }
}


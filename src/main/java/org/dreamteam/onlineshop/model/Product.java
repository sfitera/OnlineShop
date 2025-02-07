package org.dreamteam.onlineshop.model;

import jakarta.persistence.*;
import lombok.*;
import org.dreamteam.onlineshop.model.enums.Category;

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
    @Column(nullable = false)
    @Builder.Default
    private boolean productAvailability = false;
    private String productImage;
    @Enumerated(EnumType.STRING)
    private Category productCategory;
    private String productAuthor;
    @Column(nullable = false, length = 1000)
    private String productDescription;

    public Product(String productName, double productPrice, int productQuantity,String productImage, Category productCategory, String productAuthor, String productDescription) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productImage = productImage;
        this.productCategory = productCategory;
        this.productAuthor = productAuthor;
        this.productDescription = productDescription;
        this.productAvailability = productQuantity > 0;
    }

    public boolean getProductAvailability() {
        return productAvailability;
    }
}


package org.dreamteam.onlineshop.model.DTOs;

import lombok.Data;
import org.dreamteam.onlineshop.model.enums.Category;

@Data
public class ProductDTO {
    private String productName;
    private Double productPrice;
    private int productQuantity;
    private String productImage;
    private Category productCategory;
    private String productAuthor;
    private String productDescription;
}

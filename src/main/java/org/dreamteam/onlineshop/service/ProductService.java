package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.DTOs.ProductDTO;
import org.dreamteam.onlineshop.model.Product;
import org.dreamteam.onlineshop.model.enums.Category;

import java.util.List;

public interface ProductService {


    Product addProduct(ProductDTO productDTO);

    void updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);

    Product getProductById(Long id);

    List<Product> getProductsByName(String name);

    List<Product> getProductsByCategory(Category category);

    List<Product> getProductsByAuthor(String author);

    List<Product> getAllProducts();

}
package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.Product;
import org.dreamteam.onlineshop.model.enums.Category;

import java.util.List;

public interface ProductService {


    void addProduct(Product product);

    void updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    Product getProductById(Long id);

    List<Product> getProductsByName(String name);

    List<Product> getProductsByCategory(Category category);

    List<Product> getProductsByAuthor(String author);

    List<Product> getAllProducts();

}
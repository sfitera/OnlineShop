package org.dreamteam.onlineshop.service;

import lombok.NonNull;
import org.dreamteam.onlineshop.model.Category;
import org.dreamteam.onlineshop.model.Product;

import java.util.List;

public interface ProductService {


    Product addProduct(String name, Double price, int quantity, Boolean availability, String image, Category category, String author, String description);
    Product updateProduct(Long id, Product updateProduct);
    Product getProduct(Long id);
    void deleteProduct(Long id);

    List<Product> getProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByAuthor(String author);
    List<Product> getProductsByCategoryAndAuthor(String category, String author);
    List<Product> getAllProducts();
    
}

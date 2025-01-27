package org.dreamteam.onlineshop.service;

import lombok.NonNull;
import org.dreamteam.onlineshop.model.Product;

import java.util.List;

public interface ProductService {


    Product addProduct(@NonNull String name, @NonNull Double price, String productImage, @NonNull String category, @NonNull String author, Boolean availability, @NonNull String description);
    Product updateProduct(String name, Double price, String productImage, String category, String author, Boolean availability, String description);
    Product getProduct(Long id);
    boolean deleteProduct(Long id);

    List<Product> getProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByAuthor(String author);
    List<Product> getProductsByCategoryAndAuthor(String category, String author);
    List<Product> getAllProducts();
    
}

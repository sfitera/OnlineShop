package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.model.Product;
import org.dreamteam.onlineshop.model.enums.Category;

import java.util.List;

public interface ProductService {


    void  addProduct(Product product);
    Product updateProduct(Long id, Product updateProduct);
    Product getProduct(Long id);
    Product getProductByName(String name);
    void deleteProduct(Long id);

    List<Product> getProducts();
    List<Product> getProductsByCategory(Category category);
    List<Product> getProductsByAuthor(String author);
    List<Product> getAllProducts();
    
}

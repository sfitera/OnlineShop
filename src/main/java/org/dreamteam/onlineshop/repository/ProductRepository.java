package org.dreamteam.onlineshop.repository;

import org.dreamteam.onlineshop.model.Category;
import org.dreamteam.onlineshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //TODO: prerobit
//    // Find all products containing a specific name
//    List<Product> findByNameContainingIgnoreCase(String name);
//
//    // Find all products by category
//    List<Product> findByCategoryContainingIgnoreCase(List<Category> categories);
//
//    // Find all products by availability
//    List<Product> findByAvailabilityContainingIgnoreCase(String availability);
//
//    // Find all products by author
//    List<Product> findByAuthorContainingIgnoreCase(String author);
//
//    // Find all products within a price range
//    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
}

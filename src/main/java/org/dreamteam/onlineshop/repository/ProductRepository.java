package org.dreamteam.onlineshop.repository;

import org.dreamteam.onlineshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find all products containing a specific name
    List<Product> findByNameContainingIgnoreCase(String name);

    // Find all products by category
    List<Product> findByCategoryContainingIgnoreCase(String category);

    // Find all products by availability
    List<Product> findByAvailabilityContainingIgnoreCase(Product availability);

    // Find all products by author
    List<Product> findByAuthorContainingIgnoreCase(String author);

    // Find all products within a price range
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}

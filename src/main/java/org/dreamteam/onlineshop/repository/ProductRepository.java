package org.dreamteam.onlineshop.repository;

import org.dreamteam.onlineshop.model.Category;
import org.dreamteam.onlineshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);

    List<Product> findAllByAuthor(String author);

    Product findByName(String name);

}

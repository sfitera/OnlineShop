package org.dreamteam.onlineshop.repository;

import org.dreamteam.onlineshop.model.Product;
import org.dreamteam.onlineshop.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByProductCategory(Category category);

    List<Product> findAllByProductAuthor(String author);

    Product findByProductName(String name);

}

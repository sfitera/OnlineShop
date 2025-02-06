package org.dreamteam.onlineshop.repository;

import org.dreamteam.onlineshop.model.Product;
import org.dreamteam.onlineshop.model.enums.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

        @Autowired
        private ProductRepository productRepository;

    @BeforeEach
        public void setUp() {
            // Setting up test data
            Product product1 = new Product();
            product1.setProductName("Book of Spring");
            product1.setProductAuthor("John Doe");
            product1.setProductCategory(Category.SCIENCE);

            Product product2 = new Product();
            product2.setProductName("Spring Boot Essentials");
            product2.setProductAuthor("Jane Smith");
            product2.setProductCategory(Category.COOKING);

        Product product3 = new Product();
            product3.setProductName("Advanced Java");
            product3.setProductAuthor("John Doe");
            product3.setProductCategory(Category.TRAVEL);

            // Save products to repository
            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
        }

        @Test
        public void testGetProductsByProductCategory() {
            List<Product> books = productRepository.getProductsByProductCategory(Category.TRAVEL);

            assertNotNull(books, "Product list should not be null");
            assertEquals(3, books.size(), "There should be 3 products in the BOOK category");
        }

        @Test
        public void testFindAllByProductAuthorContainingIgnoreCase() {
            List<Product> productsByAuthor = productRepository.findAllByProductAuthorContainingIgnoreCase("john doe");

            assertNotNull(productsByAuthor, "Product list should not be null");
            assertEquals(2, productsByAuthor.size(), "There should be 2 products by 'John Doe' (case insensitive)");
        }

        @Test
        public void testFindAllByProductNameContainingIgnoreCase() {
            List<Product> productsByName = productRepository.findAllByProductNameContainingIgnoreCase("spring");

            assertNotNull(productsByName, "Product list should not be null");
            assertEquals(2, productsByName.size(), "There should be 2 products with 'spring' in their name");
        }

        @Test
        public void testGetProductsByProductCategoryNoResults() {
            List<Product> products = productRepository.getProductsByProductCategory(Category.TRAVEL);

            assertNotNull(products, "Product list should not be null");
            assertTrue(products.isEmpty(), "There should be no products in the ELECTRONICS category");
        }
    }


package org.dreamteam.onlineshop.repository;

import org.dreamteam.onlineshop.model.Product;
import org.dreamteam.onlineshop.model.enums.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {

        Product product1 = new Product();
        product1.setProductName("Book of Spring");
        product1.setProductPrice(19.99);
        product1.setProductAuthor("John Doe");
        product1.setProductCategory(Category.SCIENCE);
        product1.setProductDescription("Test Description");

        Product product2 = new Product();
        product2.setProductName("Spring Boot Essentials");
        product2.setProductPrice(9.99);
        product2.setProductAuthor("Jane Smith");
        product2.setProductCategory(Category.COOKING);
        product2.setProductDescription("Test Description");

        Product product3 = new Product();
        product3.setProductName("Advanced Java");
        product3.setProductPrice(15.99);
        product3.setProductAuthor("John Doe");
        product3.setProductCategory(Category.TRAVEL);
        product3.setProductDescription("Test Description");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }

    @Test
    public void testGetProductsByProductCategory() {
        List<Product> books = productRepository.getProductsByProductCategory(Category.TRAVEL);

        assertNotNull(books, "Product list should not be null");
        assertEquals(1, books.size(), "There should be 3 products in the BOOK category");
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
        List<Product> products = productRepository.getProductsByProductCategory(Category.EDUCATION);

        assertNotNull(products, "Product list should not be null");
        assertTrue(products.isEmpty(), "There should be no products in the EDUCATION category");
    }
}


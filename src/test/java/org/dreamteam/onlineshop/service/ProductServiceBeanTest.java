package org.dreamteam.onlineshop.service;

import org.dreamteam.onlineshop.mapper.EntityMapper;
import org.dreamteam.onlineshop.model.DTOs.ProductDTO;
import org.dreamteam.onlineshop.model.Product;
import org.dreamteam.onlineshop.model.enums.Category;
import org.dreamteam.onlineshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestPropertySource(locations = "classpath:application-test.properties")
class ProductServiceBeanTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private EntityMapper entityMapper;

    @InjectMocks
    private ProductServiceBean productService;

    private ProductDTO testProductDTO;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testProductDTO = new ProductDTO();
        testProductDTO.setProductName("Test Product");
        testProductDTO.setProductPrice(19.99);
        testProductDTO.setProductQuantity(10);
        testProductDTO.setProductImage("image.jpg");
        testProductDTO.setProductCategory(Category.COOKING);
        testProductDTO.setProductAuthor("Test Author");
        testProductDTO.setProductDescription("Test Description");

        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setProductName("Test Product");
        testProduct.setProductPrice(19.99);
        testProduct.setProductQuantity(10);
        testProduct.setProductAvailability(true);
        testProduct.setProductImage("image.jpg");
        testProduct.setProductCategory(Category.COOKING);
        testProduct.setProductAuthor("Test Author");
        testProduct.setProductDescription("Test Description");
    }

    //TODO: opytat sa Lucky, toto z nejakeho dovodu nefunguje
    @Test
    void testAddProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);
        Product result = productService.addProduct(testProductDTO);
        assertNotNull(result, "Product should be created successfully");
        verify(productRepository, times(1)).save(any(Product.class));
        assertTrue(result.getProductAvailability(), "Product availability should be true");
        assertEquals(10, result.getProductQuantity(), "Product quantity should be 10");
    }
    //TODO: opytat sa Lucky, toto z nejakeho dovodu nefunguje
    @Test
    void testUpdateProduct_Success() {
        ProductDTO updatedProduct = new ProductDTO();
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductPrice(25.99);
        updatedProduct.setProductQuantity(5);

        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));

        productService.updateProduct(1L, updatedProduct);

        verify(productRepository, times(1)).save(testProduct);
        assertEquals("Updated Product", testProduct.getProductName());
        assertEquals(25.99, testProduct.getProductPrice());
        assertEquals(5, testProduct.getProductQuantity());
    }

    @Test
    void testUpdateProduct_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                productService.updateProduct(1L, testProductDTO));

        assertEquals("Product with id 1 does not exist", exception.getMessage());
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testDeleteProduct_Success() {
        when(productRepository.existsById(1L)).thenReturn(true);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteProduct_NotFound() {
        when(productRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                productService.deleteProduct(1L));

        assertEquals("Product with id1does not exist", exception.getMessage());


        verify(productRepository, never()).deleteById(1L);
    }

    @Test
    void testGetProductById_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));

        Product foundProduct = productService.getProductById(1L);

        assertNotNull(foundProduct);
        assertEquals("Test Product", foundProduct.getProductName());
    }

    @Test
    void testGetProductById_NotFound() {

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                productService.getProductById(1L));

        assertEquals("Product with id1does not exist", exception.getMessage());
    }

    @Test
    void testGetProductsByName_Success() {
        List<Product> products = new ArrayList<>();
        products.add(testProduct);

        when(productRepository.findAllByProductNameContainingIgnoreCase("Test")).thenReturn(products);

        List<Product> result = productService.getProductsByName("Test");

        assertEquals(1, result.size());
        assertEquals("Test Product", result.getFirst().getProductName());
    }

    @Test
    void testGetProductsByName_NotFound() {
        when(productRepository.findAllByProductNameContainingIgnoreCase("Nonexistent")).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                productService.getProductsByName("Nonexistent"));

        assertEquals("No products found with name containing: Nonexistent", exception.getMessage());
    }

    @Test
    void testGetProductsByCategory_Success() {
        List<Product> products = new ArrayList<>();
        products.add(testProduct);

        when(productRepository.getProductsByProductCategory(Category.COOKING)).thenReturn(products);

        List<Product> result = productService.getProductsByCategory(Category.COOKING);

        assertEquals(1, result.size());
        assertEquals(Category.COOKING, result.getFirst().getProductCategory());
    }

    @Test
    void testGetProductsByCategory_NotFound() {

        when(productRepository.getProductsByProductCategory(Category.TRAVEL)).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                productService.getProductsByCategory(Category.TRAVEL));

        assertEquals("No products found for category: TRAVEL", exception.getMessage());
    }

    @Test
    void testGetProductsByAuthor_Success() {
        List<Product> products = new ArrayList<>();
        products.add(testProduct);

        when(productRepository.findAllByProductAuthorContainingIgnoreCase("Test")).thenReturn(products);

        List<Product> result = productService.getProductsByAuthor("Test");

        assertEquals(1, result.size());
        assertEquals("Test Author", result.getFirst().getProductAuthor());
    }

    @Test
    void testGetProductsByAuthor_NotFound() {
        when(productRepository.findAllByProductAuthorContainingIgnoreCase("Unknown")).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                productService.getProductsByAuthor("Unknown"));

        assertEquals("No products found for author containing: Unknown", exception.getMessage());
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(testProduct);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(1, result.size());
    }
}
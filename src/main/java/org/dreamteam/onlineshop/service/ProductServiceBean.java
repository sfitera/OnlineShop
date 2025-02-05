package org.dreamteam.onlineshop.service;

import lombok.extern.slf4j.Slf4j;
import org.dreamteam.onlineshop.mapper.EntityMapper;
import org.dreamteam.onlineshop.model.DTOs.ProductDTO;
import org.dreamteam.onlineshop.model.Product;
import org.dreamteam.onlineshop.model.enums.Category;
import org.dreamteam.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service

public class ProductServiceBean implements ProductService {

    private final ProductRepository productRepository;
    private final EntityMapper entityMapper;

    @Autowired
    public ProductServiceBean(ProductRepository productRepository, EntityMapper entityMapper) {
        this.productRepository = productRepository;
        this.entityMapper = entityMapper;
    }


    @Override
    public void addProduct(ProductDTO productDTO) {
        Product product = entityMapper.toProductEntity(productDTO);
        product.setProductAvailability(product.getProductQuantity() > 0);
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, ProductDTO productDTO) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with id " + id + " does not exist"));

        Product updateData = entityMapper.toProductEntity(productDTO);

        Optional.ofNullable(updateData.getProductName()).ifPresent(product::setProductName);
        Optional.ofNullable(updateData.getProductPrice()).ifPresent(product::setProductPrice);
        Optional.of(updateData.getProductQuantity()).filter(q -> q > 0).ifPresent(product::setProductQuantity);
        Optional.of(updateData.getProductAvailability()).ifPresent(product::setProductAvailability);
        Optional.ofNullable(updateData.getProductImage()).ifPresent(product::setProductImage);
        Optional.ofNullable(updateData.getProductCategory()).ifPresent(product::setProductCategory);
        Optional.ofNullable(updateData.getProductAuthor()).filter(author -> !author.trim().isEmpty()).ifPresent(product::setProductAuthor);
        Optional.ofNullable(updateData.getProductDescription()).filter(desc -> !desc.trim().isEmpty()).ifPresent(product::setProductDescription);

        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product with id" + id + "does not exist");
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with id" + id + "does not exist"));
    }

    @Override
    public List<Product> getProductsByName(String name) {
        List<Product> products = productRepository.findAllByProductNameContainingIgnoreCase(name);
        if (products.isEmpty()) {
            throw new IllegalArgumentException("No products found with name containing: " + name);
        }
        return products;
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        List<Product> products = productRepository.getProductsByProductCategory(category);
        if (products.isEmpty()) {
            throw new IllegalArgumentException("No products found for category: " + category);
        }
        return products;
    }

    @Override
    public List<Product> getProductsByAuthor(String author) {
        List<Product> products = productRepository.findAllByProductAuthorContainingIgnoreCase(author);
        if (products.isEmpty()) {
            throw new IllegalArgumentException("No products found for author containing: " + author);
        }
        return products;
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}



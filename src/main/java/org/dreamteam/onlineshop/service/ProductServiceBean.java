package org.dreamteam.onlineshop.service;

import lombok.extern.slf4j.Slf4j;
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

    @Autowired
    public ProductServiceBean(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public void addProduct(Product product) {
        product.setProductAvailability(product.getProductQuantity() > 0);
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, Product updateData) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with id " + id + " does not exist"));

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
    public Product getProductByName(String name) {
        return productRepository.findByProductName(name);
    }


    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findAllByProductCategory(category);
    }

    @Override
    public List<Product> getProductsByAuthor(String author) {
        return productRepository.findAllByProductAuthor(author);
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


}



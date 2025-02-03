package org.dreamteam.onlineshop.service;

import lombok.extern.slf4j.Slf4j;
import org.dreamteam.onlineshop.model.Product;


import org.dreamteam.onlineshop.model.enums.Category;
import org.dreamteam.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;



@Slf4j
@Service

public class ProductServiceBean implements  ProductService {

    int amount = 0;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceBean(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public void  addProduct(Product product) {
        productRepository.save(product);
    }


    @Override
    public Product updateProduct(Long id, Product updateProduct) {
        var product = productRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with id " + id + " does not exist"));
        var updated = new Product();
        if (StringUtils.hasText(updateProduct.getProductName())) {
            updated.setProductName(updateProduct.getProductName());
        } else {
            updated.setProductName(product.getProductName());
        }

        if (updateProduct.getProductPrice() != 0) updated.setProductPrice(product.getProductPrice());
        else {
            updated.setProductPrice(product.getProductPrice());
        }

        if (updateProduct.getProductQuantity() != 0)
            updated.setProductQuantity(product.getProductQuantity());
        else {
            updated.setProductQuantity(product.getProductQuantity());
        }


        if (updateProduct.getProductCategory() != null) {
            updated.setProductCategory(updateProduct.getProductCategory());
        } else {
            updated.setProductCategory(product.getProductCategory());
        }

        if (StringUtils.hasText(updateProduct.getProductAuthor())) {
            updated.setProductAuthor(updateProduct.getProductAuthor());
        } else {
            updated.setProductAuthor(product.getProductAuthor());
        }

        if (StringUtils.hasText(updateProduct.getProductDescription())) {
            updated.setProductDescription(updateProduct.getProductDescription());
        } else {
            updated.setProductDescription(product.getProductDescription());
        }

        productRepository.save(updated);
        log.info("Product updated: {}", updated);
        return updated;

    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product with id" + id + "does not exist");
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with id" + id + "does not exist"));
    }


    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public List<Product> getProductsByAuthor(String author) {
        return productRepository.findAllByAuthor(author);
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name);

    }
}



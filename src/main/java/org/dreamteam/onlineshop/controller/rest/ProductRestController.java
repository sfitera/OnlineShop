package org.dreamteam.onlineshop.controller.rest;

import org.dreamteam.onlineshop.model.Product;
import org.dreamteam.onlineshop.model.enums.Category;
import org.dreamteam.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Controller", description = "API na správu produktov")
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    @Operation(summary = "Pridaj nový produkt")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    @Operation(summary = "Aktualizuj produkt podla ID")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        productService.updateProduct(id, updatedProduct);
        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Zmaz produkt podla ID")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Získaj zoznam všetkých produktov podla ID produktu")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return product != null ? new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Získaj produkty podľa mena")
    public ResponseEntity<List<Product>> getProductsByName(@PathVariable String name) {
        List<Product> products = productService.getProductsByName(name);
        return products != null ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Získaj zoznam všetkých produktov podla kategorie")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Category category) {
        List<Product> productsByCategory = productService.getProductsByCategory(category);
        return productsByCategory != null ? new ResponseEntity<>(productsByCategory, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/author/{author}")
    @Operation(summary = "Získaj zoznam všetkých produktov podla autora")
    public ResponseEntity<List<Product>> getProductsByAuthor(@PathVariable String author) {
        List<Product> productsByAuthor = productService.getProductsByAuthor(author);
        return productsByAuthor != null ? new ResponseEntity<>(productsByAuthor, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    @Operation(summary = "Získaj zoznam všetkých produktov")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
}
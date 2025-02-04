package org.dreamteam.onlineshop.controller;

import lombok.extern.slf4j.Slf4j;
import org.dreamteam.onlineshop.model.Product;
import org.dreamteam.onlineshop.model.enums.Category;
import org.dreamteam.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add-product")
    public String getProductForm() {
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "edit-product";
    }

    @PatchMapping("/{id}/update")
    public String updateProduct(
            @PathVariable("id") Long id,
            @ModelAttribute Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String showProductDetails(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-details";
    }

    @GetMapping("/")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/category")
    public String getProductsByCategory(@RequestParam Category category, Model model) {
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/author")
    public String getProductsByAuthor(@RequestParam String author, Model model) {
        List<Product> products = productService.getProductsByAuthor(author);
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/name")
    public String getProductByName(@RequestParam String name, Model model) {
        List<Product> products = productService.getProductsByName(name);
        model.addAttribute("products", products);
        return "product-list-name";
    }
}

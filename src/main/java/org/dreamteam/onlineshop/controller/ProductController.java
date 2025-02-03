package org.dreamteam.onlineshop.controller;

import ch.qos.logback.core.model.Model;
import lombok.extern.slf4j.Slf4j;
import org.dreamteam.onlineshop.model.Category;
import org.dreamteam.onlineshop.model.Product;
import org.dreamteam.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    // Zobrazení všech produktů
    // View all products
    @GetMapping("/")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-list";
    }

    // Zobrazení detailů konkrétního produktu
    // View details of a specific product
    @GetMapping("/{id}")
    public String showProductDetails(@PathVariable Long id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "product-details";
    }

    // Formulář pro přidání nového produktu
    // Form for adding new product
    @GetMapping("/add-product")
    public String getProductForm() {
        return "add-product";
    }

    // Zpracování formuláře pro přidání produktu
    // Processing product add form
    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute Product product){
        productService.addProduct(
                product.getName(),
                product.getPrice(),
                product.getImage(),
                product.getCategory(),
                product.getAuthor(),
                product.getQuantity(),
                product.getAvailability(),
                product.getDescription()
        );
       return "redirect:/products";
    }

    // Formulář pro úpravu existujícího produktu
    // Form for editing existing product
    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "edit-product";
    }

    // Zpracování formuláře pro úpravu produktu
    // Process product edit form
    @PatchMapping("/{id}/update")
    public String updateProduct(

            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam String productImage,
            @RequestParam String category,
            @RequestParam String author,
            @RequestParam String description) {
        return "redirect:/products";
    }

    // Smazání produktu
    // Delete product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    // Filtrování produktů podle kategorie
    // Filter products by category
    @GetMapping("/category")
    public String getProductsByCategory(@RequestParam Category category, Model model) {
        List<Product> products = productService.getProductsByCategory(String.valueOf(category));
        model.addAttribute("products", products);
        return "product-list";
    }

    // Filtrování produktů podle autora
    // Filter products by author
    @GetMapping("/author")
    public String getProductsByAuthor(@RequestParam String author, Model model) {
        List<Product> products = productService.getProductsByAuthor(author);
        model.addAttribute("products", products);
        return "product-list";
    }

    // Filtrování produktů podle kategorie a autora
    // Filter products by category and author
    @GetMapping("/filter")
    public String getProductsByCategoryAndAuthor(@RequestParam Category category, @RequestParam String author, Model model) {
        List<Product> products = productService.getProductsByCategoryAndAuthor(String.valueOf(category), author);
        model.addAttribute("products", products);
        return "product-list";
    }
}

package org.dreamteam.onlineshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.dreamteam.onlineshop.model.DTOs.OrderItemDTO;
import org.dreamteam.onlineshop.model.OrderItem;
import org.dreamteam.onlineshop.repository.OrderItemRepository;
import org.dreamteam.onlineshop.service.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/orderitem")
@Tag(name = "Order Item Controller", description = "API na správu poloziek v objednavke")
@CrossOrigin(origins = "http://localhost:5173/")
public class OrderItemRestController {

    private final OrderItemService orderItemService;
    private final OrderItemRepository orderItemRepository;

    public OrderItemRestController(OrderItemService orderItemService, OrderItemRepository orderItemRepository) {
        this.orderItemService = orderItemService;
        this.orderItemRepository = orderItemRepository;
    }

    @PostMapping("/add/")
    @Operation(summary = "Pridaj položku do košíka")
    public ResponseEntity<OrderItem> addOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        OrderItem addedItem = orderItemService.addOrderItem(orderItemDTO);

        if (addedItem == null || addedItem.getProduct() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // ✅ Vrátime celý objekt `OrderItem`, aby frontend vedel vykresliť produkt
        return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{orderItemId}")
    @Operation(summary = "Aktualizuj položku podľa ID")
    public ResponseEntity<?> updateOrderItem(@PathVariable Long orderItemId, @RequestBody Map<String, Integer> requestBody) {
        int quantity = requestBody.get("quantity");
        log.info("🛒 PATCH Update OrderItem ID: " + orderItemId + " New Quantity: " + quantity);

        Optional<OrderItem> orderItem = orderItemRepository.findById(orderItemId);
        if (orderItem.isEmpty()) {
            log.warn("⚠️ OrderItem not found: " + orderItemId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order item not found");
        }

        try {
            orderItemService.updateOrderItem(orderItemId, quantity);
            return ResponseEntity.ok("✅ Order item updated successfully");
        } catch (Exception e) {
            log.error("❌ Chyba pri aktualizácii objednávky:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Chyba pri aktualizácii objednávky.");
        }
    }



    @DeleteMapping("/delete/{orderItemId}")
    @Operation(summary = "Zmaž položku podľa ID")
    public ResponseEntity<String> deleteOrderItem(@PathVariable Long orderItemId) {
        log.info("🗑️ DELETE OrderItem ID: " + orderItemId);

        Optional<OrderItem> orderItem = orderItemRepository.findById(orderItemId);
        if (orderItem.isEmpty()) {
            log.warn("⚠️ OrderItem not found: " + orderItemId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order item not found");
        }

        orderItemService.deleteOrderItem(orderItemId);
        return ResponseEntity.ok("✅ Order item deleted successfully");
    }




    @GetMapping("/{orderItemId}")
    @Operation(summary = "Získaj polozku podla ID")
    public ResponseEntity<OrderItem> getOrderItem(@PathVariable Long orderItemId) {
        OrderItem orderItem = orderItemService.getOrderItem(orderItemId);
        return orderItem != null ? new ResponseEntity<>(orderItem, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    @Operation(summary = "Získaj zoznam všetkých poloziek")
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        System.out.println("🔍 Načítané položky: " + orderItems); // Debugging log
        return orderItems != null ? new ResponseEntity<>(orderItems, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/clear-cart/{userId}")
    @Operation(summary = "Vyprázdni košík po vytvorení objednávky")
    public ResponseEntity<String> clearCart(@PathVariable Long userId) {
        orderItemService.clearCart(userId);
        return ResponseEntity.ok("Košík bol úspešne vyprázdnený.");
    }

    @GetMapping("/cart")
    public List<OrderItem> getCartItems() {
        return orderItemService.getCartItems();
    }

}

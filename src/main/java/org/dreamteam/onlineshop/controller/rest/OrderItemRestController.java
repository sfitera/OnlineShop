package org.dreamteam.onlineshop.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.dreamteam.onlineshop.model.DTOs.OrderItemDTO;
import org.dreamteam.onlineshop.model.OrderItem;
import org.dreamteam.onlineshop.service.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderitem")
@Tag(name = "Order Item Controller", description = "API na správu poloziek v objednavke")
@CrossOrigin(origins = "http://localhost:5173/")
public class OrderItemRestController {

    private final OrderItemService orderItemService;

    public OrderItemRestController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/add/")
    @Operation(summary = "Pridaj polozku")
    public ResponseEntity<String> addOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        orderItemService.addOrderItem(orderItemDTO);
        return new ResponseEntity<>("Order item added successfully", HttpStatus.CREATED);
    }

    @PatchMapping("/update/{orderItemId}")
    @Operation(summary = "Aktualizuj polozku podla ID")
    public ResponseEntity<String> updateOrderItem(@PathVariable Long orderItemId, @RequestParam int quantity) {
        orderItemService.updateOrderItem(orderItemId, quantity);
        return new ResponseEntity<>("Order item updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderItemId}")
    @Operation(summary = "Zmaz polozku podla ID")
    public ResponseEntity<String> deleteOrderItem(@PathVariable Long orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
        return new ResponseEntity<>("Order item deleted successfully", HttpStatus.OK);
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
        return orderItems != null ? new ResponseEntity<>(orderItems, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

package org.dreamteam.onlineshop.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.OrderItem;
import org.dreamteam.onlineshop.model.enums.OrderStatus;
import org.dreamteam.onlineshop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order Controller", description = "API na správu objednavok")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    @Operation(summary = "Vytvor novu objednavku")
    public ResponseEntity<String> createOrder(@RequestBody Order order){
        orderService.createOrder(order);
        return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
    }

    @PatchMapping("/update/{orderId}")
    @Operation(summary = "Aktualizuj stav objednavky podla ID")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus orderStatus) {
        orderService.updateOrderStatus(orderId, orderStatus);
        return new ResponseEntity<>("Order updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderId}")
    @Operation(summary = "Zmaz objednavku podla ID")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Získaj objednavku podla ID")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId){
        Order order = orderService.getOrderById(orderId);
        return order != null ? new ResponseEntity<>(order, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/")
    @Operation(summary = "Získaj zoznam všetkých objednavok")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        return orders != null ? new ResponseEntity<>(orders, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/user/{userId}")
    @Operation(summary = "Získaj zoznam všetkých produktov patriace uzivatelovi")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId){
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return orders != null ? new ResponseEntity<>(orders, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/update/{orderId}/orderitem/{itemId}")
    @Operation(summary = "Pridaj polozku do objednavky")
    public ResponseEntity<String> addOrderItem(@PathVariable Long orderId,@PathVariable OrderItem itemId){
        orderService.addOrderItem(orderId, itemId);
        return new ResponseEntity<>("Order item added successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderId}/orderitem/{itemId}")
    @Operation(summary = "Zmaz polozku z objednavky")
    public ResponseEntity<String> deleteOrderItem(@PathVariable Long orderId,@PathVariable Long itemId){
        orderService.deleteOrderItem(orderId, itemId);
        return new ResponseEntity<>("Order item deleted successfully", HttpStatus.OK);
    }
}

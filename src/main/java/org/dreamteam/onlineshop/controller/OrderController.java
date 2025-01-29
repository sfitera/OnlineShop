package org.dreamteam.onlineshop.controller;

import lombok.extern.slf4j.Slf4j;
import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.OrderItem;
import org.dreamteam.onlineshop.model.enums.OrderStatus;
import org.dreamteam.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {

        this.orderService = orderService;
    }

    @GetMapping("/create")
    public String showCreateOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "order-form";
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute Order order) {
        orderService.createOrder(order);
        return "redirect:/orders";
    }

    @PatchMapping("/{id}/update")
    public String updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        orderService.updateOrderStatus(id, status);
        return "redirect:/orders/";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders/";
    }

    @GetMapping("/{id}")
    public String getOrderById(@PathVariable Long id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "order-detail";
    }

    @GetMapping("/")
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order-list";
    }

    @GetMapping("/{userId}")
    public String getOrdersByUserId(@PathVariable Long userId, Model model) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        model.addAttribute("orders", orders);
        return "order-list";
    }

    @PostMapping("/{orderId}/items/add")
    public String addOrderItem(@PathVariable Long orderId,
                               @ModelAttribute OrderItem orderItem) {
        orderService.addOrderItem(orderId, orderItem);
        return "redirect:/orders/" + orderId;
    }

    @GetMapping("/{orderId}/items/{itemId}/delete")
    public String deleteOrderItem(@PathVariable Long orderId, @PathVariable Long itemId) {
        orderService.deleteOrderItem(orderId, itemId);
        return "redirect:/orders/" + orderId;
    }
}
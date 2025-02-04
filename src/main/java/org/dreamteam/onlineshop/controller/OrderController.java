package org.dreamteam.onlineshop.controller;

import lombok.extern.slf4j.Slf4j;
import org.dreamteam.onlineshop.model.DTOs.OrderDTO;
import org.dreamteam.onlineshop.model.Order;
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
    public String createOrder(@ModelAttribute OrderDTO orderDto) {
        orderService.createOrder(orderDto);
        return "redirect:/orders/";
    }

    @PatchMapping("/{id}/update")
    public String updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus orderStatus) {
        orderService.updateOrderStatus(id, orderStatus);
        return "redirect:/orders/";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders/";
    }

    @GetMapping("/{id}")
    public String getOrderById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "order-detail";
    }

    @GetMapping("/")
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order-list";
    }

    @GetMapping("/user/{userId}")
    public String getOrdersByUserId(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("orders", orderService.getOrdersByUserId(userId));
        return "user-order-list";
    }

}

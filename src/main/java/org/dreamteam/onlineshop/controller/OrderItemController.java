package org.dreamteam.onlineshop.controller;

import lombok.extern.slf4j.Slf4j;
import org.dreamteam.onlineshop.model.Order;
import org.dreamteam.onlineshop.model.OrderItem;
import org.dreamteam.onlineshop.model.Product;
import org.dreamteam.onlineshop.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders/item")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/create")
    public String showAddOrderItemForm(Model model) {
        model.addAttribute("orderItem", new OrderItem());
        return "order-item-form";
    }

//    @PostMapping("/create")
//    public String addOrderItem(@ModelAttribute OrderItem orderItem) {
//        orderItemService.addOrderItem(orderItem.getProduct(), orderItem.getQuantity());
//        return "redirect:/orders/item";
//    }

    @GetMapping("/add/{orderId}")
    public String showUpdateOrderItemForm(@PathVariable Long id, Model model) {
        model.addAttribute("orderItem", orderItemService.getOrderItem(id));
        return "order-item-form";
    }

    @PatchMapping("/update/{orderItemId}")
    public String updateOrderItem(@PathVariable Long id,
                                  @RequestParam int quantity) {
        orderItemService.updateOrderItem(id, quantity);
        return "redirect:/orders/item";
    }

    @DeleteMapping("/delete/{orderId}/{orderItemId}")
    public String deleteOrderItem(@PathVariable Long id, @PathVariable Long orderItemId) {
        orderItemService.deleteOrderItem(id, orderItemId);
        return "redirect:/orders/item";
    }

    @GetMapping("/{id}")
    public String getOrderItem(@PathVariable Long id, Model model) {
        OrderItem orderItem = orderItemService.getOrderItem(id);
        model.addAttribute("orderItem", orderItem);
        return "order-item-detail";
    }

    @GetMapping("/")
    public String getAllOrderItems(Model model) {
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        model.addAttribute("orderItems", orderItems);
        return "order-item-list";
    }
}

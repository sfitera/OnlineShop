package org.dreamteam.onlineshop.controller;

import lombok.extern.slf4j.Slf4j;
import org.dreamteam.onlineshop.service.ProductService;
import org.dreamteam.onlineshop.service.OrderService;
import org.dreamteam.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Slf4j
@Controller
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService, ProductService productService) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
    }


}

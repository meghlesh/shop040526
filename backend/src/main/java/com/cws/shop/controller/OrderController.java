package com.cws.shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cws.shop.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public ResponseEntity<String> placeOrder() {
        orderService.placeOrder();
        return ResponseEntity.ok("Order placed successfully");
    }
}
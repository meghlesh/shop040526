package com.cws.shop.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cws.shop.model.*;
import com.cws.shop.repository.*;
import com.cws.shop.service.NotificationService;
import com.cws.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final NotificationService notification;

    public OrderServiceImpl(OrderRepository orderRepository,
                            CartRepository cartRepository,
                            UserRepository userRepository,
                            NotificationService notification) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.notification = notification;
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void placeOrder() {

        User user = getCurrentUser();

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart is empty"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // Create Order
        Order order = new Order();
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;

        for (CartItem cartItem : cart.getItems()) {

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPriceAtTime());

            total += cartItem.getQuantity() * cartItem.getPriceAtTime();
            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setTotalAmount(total);

        orderRepository.save(order);

        // 🔔 Notification
        notification.sendNotification(
                user,
                "Order Placed",
                "Your order has been placed successfully",
                NotificationType.ORDER
        );

        // 🧹 Clear cart
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
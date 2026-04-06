package com.parfumerie.catalog.service;

import com.parfumerie.catalog.entity.CartItem;
import com.parfumerie.catalog.entity.Order;
import com.parfumerie.catalog.entity.OrderItem;
import com.parfumerie.catalog.entity.User;
import com.parfumerie.catalog.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
        order.getItems().forEach(item -> item.setOrder(order));
        return orderRepository.save(order);
    }

    public List<Order> getOrdersForUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Transactional
    public Order createOrderFromCart(User user, Order order, List<CartItem> cartItems) {
        order.setUser(user);
        cartItems.forEach(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setParfumId(cartItem.getParfum().getIdParfum());
            orderItem.setParfumName(cartItem.getParfum().getNumeParfum());
            orderItem.setUnitPrice(cartItem.getParfum().getPret());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getParfum().getPret() * cartItem.getQuantity());
            orderItem.setOrder(order);
            order.getItems().add(orderItem);
        });

        double total = order.getItems().stream()
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();
        order.setTotalAmount(total);
        return orderRepository.save(order);
    }
}

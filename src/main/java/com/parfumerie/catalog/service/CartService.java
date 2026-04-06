package com.parfumerie.catalog.service;

import com.parfumerie.catalog.entity.CartItem;
import com.parfumerie.catalog.entity.User;
import com.parfumerie.catalog.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;

    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> getCartForUser(User user) {
        return cartItemRepository.findByUser(user);
    }

    public CartItem saveCartItem(CartItem item) {
        return cartItemRepository.save(item);
    }

    public void removeCartItem(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }

    public void clearCart(User user) {
        cartItemRepository.findByUser(user).forEach(cartItemRepository::delete);
    }
}

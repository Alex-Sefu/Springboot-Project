package com.parfumerie.catalog.repository;

import com.parfumerie.catalog.entity.CartItem;
import com.parfumerie.catalog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
}

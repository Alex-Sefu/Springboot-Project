package com.parfumerie.catalog.repository;

import com.parfumerie.catalog.entity.Order;
import com.parfumerie.catalog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}

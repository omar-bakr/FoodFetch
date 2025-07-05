package com.omarbakr.foodfetch.repository;

import com.omarbakr.foodfetch.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

package com.omarbakr.foodfetch.repository;

import com.omarbakr.foodfetch.model.OrderSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSessionRepository extends JpaRepository<OrderSession, Long> {
    OrderSession findBySessionCode(String sessionCode);
}
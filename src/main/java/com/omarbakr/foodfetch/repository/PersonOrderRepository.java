package com.omarbakr.foodfetch.repository;

import com.omarbakr.foodfetch.model.PersonOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonOrderRepository extends JpaRepository<PersonOrder, Long> {
}

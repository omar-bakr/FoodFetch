package com.omarbakr.foodfetch.repository;

import com.omarbakr.foodfetch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

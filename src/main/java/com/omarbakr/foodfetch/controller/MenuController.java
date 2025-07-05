package com.omarbakr.foodfetch.controller;

import com.omarbakr.foodfetch.dto.RestaurantMenuResponse;
import com.omarbakr.foodfetch.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantMenuResponse>> getAllMenus() {
        return ResponseEntity.ok(menuService.getAllMenus());
    }

    @GetMapping("/{restaurantName}")
    public ResponseEntity<RestaurantMenuResponse> getMenuByName(@PathVariable String restaurantName) {
        return ResponseEntity.ok(menuService.getMenuByName(restaurantName));
    }
}

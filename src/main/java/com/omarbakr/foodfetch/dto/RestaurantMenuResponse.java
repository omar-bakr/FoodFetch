package com.omarbakr.foodfetch.dto;

import java.math.BigDecimal;
import java.util.Map;

public record RestaurantMenuResponse(
        String name,
        Map<String, Map<String, BigDecimal>> menu) {
}


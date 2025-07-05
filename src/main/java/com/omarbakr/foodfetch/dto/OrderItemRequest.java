package com.omarbakr.foodfetch.dto;

import java.math.BigDecimal;

public record OrderItemRequest(
        String itemName,
        int quantity,
        BigDecimal itemPrice
) {
}

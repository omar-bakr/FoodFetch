package com.omarbakr.foodfetch.dto;

import java.math.BigDecimal;

public record OrderItemResponse(
        String itemName,
        int quantity,
        BigDecimal itemPrice
) {
}

package com.omarbakr.foodfetch.dto;

import java.math.BigDecimal;
import java.util.List;

public record ConsolidatedItemDTO(
        String itemName,
        int totalQuantity,
        BigDecimal totalPrice,
        List<String> orderedBy
) {
}

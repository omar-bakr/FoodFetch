package com.omarbakr.foodfetch.dto;

import java.math.BigDecimal;

public record OrderSessionResponse(
        Long id,
        String restaurant,
        String notes,
        String sessionCode,
        BigDecimal extraFees,
        String status
) {
}

package com.omarbakr.foodfetch.dto;

import java.math.BigDecimal;

public record PaymentSummaryDTO(
        String personName,
        BigDecimal orderTotal,
        BigDecimal extraFeesShare,
        BigDecimal finalTotal
) {
}

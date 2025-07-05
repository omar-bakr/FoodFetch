package com.omarbakr.foodfetch.dto;

import java.math.BigDecimal;

public record CloseSessionRequest(
        BigDecimal extraFees
) {
}

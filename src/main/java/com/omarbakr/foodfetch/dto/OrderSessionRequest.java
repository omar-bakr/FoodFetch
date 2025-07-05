package com.omarbakr.foodfetch.dto;

public record OrderSessionRequest(
        String restaurant,
        String notes,
        String sessionCode
) {
}

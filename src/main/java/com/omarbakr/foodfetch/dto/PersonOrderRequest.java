package com.omarbakr.foodfetch.dto;

import java.util.List;

public record PersonOrderRequest(
        String personName,
        List<OrderItemRequest> orderItems
) {
}

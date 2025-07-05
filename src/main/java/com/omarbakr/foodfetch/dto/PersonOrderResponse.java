package com.omarbakr.foodfetch.dto;

import java.util.List;

public record PersonOrderResponse(
        String personName,
        List<OrderItemResponse> orderItems
) {
}

package com.omarbakr.foodfetch.mapper;

import com.omarbakr.foodfetch.dto.OrderSessionRequest;
import com.omarbakr.foodfetch.dto.OrderSessionResponse;
import com.omarbakr.foodfetch.model.OrderSession;

public class OrderSessionMapper {

    public static OrderSessionResponse toResponse(OrderSession entity) {
        return new OrderSessionResponse(
            entity.getId(),
            entity.getRestaurant(),
            entity.getNotes(),
            entity.getSessionCode(),
            entity.getExtraFees(),
            entity.getStatus().name()
        );
    }

    public static OrderSession toEntity(OrderSessionRequest dto) {
        OrderSession entity = new OrderSession();
        entity.setRestaurant(dto.restaurant());
        entity.setNotes(dto.notes());
        entity.setSessionCode(dto.sessionCode());
        return entity;
    }
}

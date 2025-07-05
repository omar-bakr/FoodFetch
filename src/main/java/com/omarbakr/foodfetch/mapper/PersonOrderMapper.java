package com.omarbakr.foodfetch.mapper;

import com.omarbakr.foodfetch.dto.*;
import com.omarbakr.foodfetch.model.OrderItem;
import com.omarbakr.foodfetch.model.PersonOrder;

import java.util.List;
import java.util.stream.Collectors;

public class PersonOrderMapper {

    public static PersonOrder toEntity(PersonOrderRequest request) {
        PersonOrder entity = new PersonOrder();
        entity.setPersonName(request.personName());
        entity.setOrderItems(
                request.orderItems().stream().map(item -> {
                    OrderItem oi = new OrderItem();
                    oi.setItemName(item.itemName());
                    oi.setQuantity(item.quantity());
                    oi.setItemPrice(item.itemPrice());
                    oi.setPersonOrder(entity); // bidirectional mapping
                    return oi;
                }).collect(Collectors.toList())
        );
        return entity;
    }

    public static PersonOrderResponse toResponse(PersonOrder entity) {
        return new PersonOrderResponse(
                entity.getPersonName(),
                entity.getOrderItems().stream().map(item ->
                        new OrderItemResponse(item.getItemName(), item.getQuantity(), item.getItemPrice())
                ).toList()
        );
    }
}

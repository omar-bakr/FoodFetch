package com.omarbakr.foodfetch.controller;

import com.omarbakr.foodfetch.dto.PersonOrderRequest;
import com.omarbakr.foodfetch.dto.PersonOrderResponse;
import com.omarbakr.foodfetch.mapper.PersonOrderMapper;
import com.omarbakr.foodfetch.model.PersonOrder;
import com.omarbakr.foodfetch.service.PersonOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sessions")
public class PersonOrderController {

    private final PersonOrderService personOrderService;

    public PersonOrderController(PersonOrderService personOrderService) {
        this.personOrderService = personOrderService;
    }

    @PostMapping("/{sessionCode}/orders")
    public ResponseEntity<PersonOrderResponse> submitPersonOrder(
            @PathVariable String sessionCode,
            @RequestBody PersonOrderRequest personOrderRequest
    ) {
        PersonOrderResponse savedOrder = personOrderService.submitOrder(sessionCode, personOrderRequest);
        return ResponseEntity.ok(savedOrder);
    }
}

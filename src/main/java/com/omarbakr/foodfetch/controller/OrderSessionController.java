package com.omarbakr.foodfetch.controller;

import com.omarbakr.foodfetch.dto.CloseSessionRequest;
import com.omarbakr.foodfetch.dto.ConsolidatedItemDTO;
import com.omarbakr.foodfetch.dto.OrderSessionRequest;
import com.omarbakr.foodfetch.dto.OrderSessionResponse;
import com.omarbakr.foodfetch.dto.PaymentSummaryDTO;
import com.omarbakr.foodfetch.mapper.OrderSessionMapper;
import com.omarbakr.foodfetch.model.OrderSession;
import com.omarbakr.foodfetch.service.OrderSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/sessions")
public class OrderSessionController {

    private final OrderSessionService orderSessionService;

    public OrderSessionController(OrderSessionService orderSessionService) {
        this.orderSessionService = orderSessionService;
    }

    @PostMapping
    public ResponseEntity<OrderSessionResponse> createSession(@RequestBody OrderSessionRequest sessionRequest) {
        OrderSessionResponse orderSessionResponse = orderSessionService.createSession(OrderSessionMapper.toEntity(sessionRequest));
        return ResponseEntity.ok(orderSessionResponse);
    }

    @GetMapping("/{sessionCode}")
    public ResponseEntity<OrderSessionResponse> getSessionByCode(@PathVariable String sessionCode) {
        return orderSessionService.getSessionByCode(sessionCode)
                .map(OrderSessionMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{sessionCode}/consolidated-items")
    public ResponseEntity<Map<String, Object>> getConsolidatedOrder(@PathVariable String sessionCode) {
        List<ConsolidatedItemDTO> result = orderSessionService.getConsolidatedOrder(sessionCode);
        return ResponseEntity.ok(Map.of(
                "sessionCode", sessionCode,
                "consolidatedItems", result
        ));
    }

    @PutMapping("/{sessionCode}/close")
    public ResponseEntity<Void> closeSession(
            @PathVariable String sessionCode,
            @RequestBody CloseSessionRequest request
    ) {
        orderSessionService.closeSession(sessionCode, request.extraFees());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{sessionCode}/summary")
    public ResponseEntity<List<PaymentSummaryDTO>> getPaymentSummary(@PathVariable String sessionCode) {
        List<PaymentSummaryDTO> result = orderSessionService.getPaymentSummary(sessionCode);
        return ResponseEntity.ok(result);
    }
}

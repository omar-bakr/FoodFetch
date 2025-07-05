package com.omarbakr.foodfetch.service;

import com.omarbakr.foodfetch.dto.ConsolidatedItemDTO;
import com.omarbakr.foodfetch.dto.OrderSessionResponse;
import com.omarbakr.foodfetch.dto.PaymentSummaryDTO;
import com.omarbakr.foodfetch.exceptions.ExtraFeesNotSetException;
import com.omarbakr.foodfetch.exceptions.InvalidSessionCodeException;
import com.omarbakr.foodfetch.exceptions.SessionAlreadyClosedException;
import com.omarbakr.foodfetch.exceptions.SessionNotClosedException;
import com.omarbakr.foodfetch.mapper.OrderSessionMapper;
import com.omarbakr.foodfetch.model.OrderItem;
import com.omarbakr.foodfetch.model.OrderSession;
import com.omarbakr.foodfetch.model.PersonOrder;
import com.omarbakr.foodfetch.model.SessionStatus;
import com.omarbakr.foodfetch.repository.OrderSessionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class OrderSessionService {

    private final OrderSessionRepository orderSessionRepository;
    private final MenuService menuService;

    public OrderSessionService(OrderSessionRepository orderSessionRepository, MenuService menuService) {
        this.orderSessionRepository = orderSessionRepository;
        this.menuService = menuService;
    }

    public OrderSessionResponse createSession(OrderSession session) {
        validateRestaurantExists(session.getRestaurant());
        return OrderSessionMapper.toResponse(orderSessionRepository.save(session));
    }


    private void validateRestaurantExists(String name) {
        menuService.getMenuByName(name); // Throws RestaurantNotFoundException
    }

    public Optional<OrderSessionResponse> getSessionByCode(String sessionCode) {
        return Optional.ofNullable(orderSessionRepository.findBySessionCode(sessionCode))
                .map(OrderSessionMapper::toResponse);
    }


    public Optional<OrderSessionResponse> getSessionById(Long id) {
        return orderSessionRepository.findById(id)
                .map(OrderSessionMapper::toResponse);
    }


    public List<ConsolidatedItemDTO> getConsolidatedOrder(String sessionCode) {
        OrderSession session = orderSessionRepository.findBySessionCode(sessionCode);
        if (session == null) throw new InvalidSessionCodeException(sessionCode);

        Map<String, ConsolidatedItemDTO> grouped = new LinkedHashMap<>();

        for (PersonOrder personOrder : session.getPersonOrders()) {
            String personName = personOrder.getPersonName();

            for (OrderItem item : personOrder.getOrderItems()) {
                String itemName = item.getItemName();
                int quantity = item.getQuantity();
                BigDecimal itemTotal = item.getItemPrice().multiply(BigDecimal.valueOf(quantity));

                grouped.compute(itemName, (key, existing) -> {
                    if (existing == null) {
                        return new ConsolidatedItemDTO(
                                itemName,
                                quantity,
                                itemTotal,
                                new ArrayList<>(List.of(personName))
                        );
                    } else {
                        List<String> updatedPeople = new ArrayList<>(existing.orderedBy());
                        updatedPeople.add(personName);

                        return new ConsolidatedItemDTO(
                                itemName,
                                existing.totalQuantity() + quantity,
                                existing.totalPrice().add(itemTotal),
                                updatedPeople
                        );
                    }
                });
            }
        }

        return new ArrayList<>(grouped.values());
    }

    public List<PaymentSummaryDTO> getPaymentSummary(String sessionCode) {
        OrderSession session = orderSessionRepository.findBySessionCode(sessionCode);
        if (session == null) throw new InvalidSessionCodeException(sessionCode);
        if (session.getStatus() != SessionStatus.CLOSED) throw new SessionNotClosedException(sessionCode);
        if (session.getExtraFees() == null) throw new ExtraFeesNotSetException(sessionCode);

        return buildPaymentSummary(session);
    }

    private List<PaymentSummaryDTO> buildPaymentSummary(OrderSession session) {
        List<PersonOrder> personOrders = session.getPersonOrders();
        int numberOfPeople = personOrders.size();
        BigDecimal feeShare = session.getExtraFees().divide(BigDecimal.valueOf(numberOfPeople), 2, RoundingMode.HALF_UP);

        List<PaymentSummaryDTO> result = new ArrayList<>();
        for (PersonOrder personOrder : personOrders) {
            BigDecimal subtotal = personOrder.getOrderItems().stream()
                    .map(item -> item.getItemPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal finalTotal = subtotal.add(feeShare);

            result.add(new PaymentSummaryDTO(
                    personOrder.getPersonName(),
                    subtotal,
                    feeShare,
                    finalTotal
            ));
        }
        return result;
    }


    public void closeSession(String sessionCode, BigDecimal extraFees) {
        OrderSession session = orderSessionRepository.findBySessionCode(sessionCode);
        if (session == null) throw new InvalidSessionCodeException(sessionCode);
        if (session.getStatus() == SessionStatus.CLOSED) throw new SessionAlreadyClosedException(sessionCode);

        session.setExtraFees(extraFees);
        session.setStatus(SessionStatus.CLOSED);
        orderSessionRepository.save(session);
    }
}

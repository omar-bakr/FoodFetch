package com.omarbakr.foodfetch.service;

import com.omarbakr.foodfetch.dto.PersonOrderRequest;
import com.omarbakr.foodfetch.dto.PersonOrderResponse;
import com.omarbakr.foodfetch.exceptions.MissingPersonNameException;
import com.omarbakr.foodfetch.exceptions.PersonOrderAlreadyExistsException;
import com.omarbakr.foodfetch.exceptions.SessionClosedException;
import com.omarbakr.foodfetch.exceptions.SessionNotFoundException;
import com.omarbakr.foodfetch.mapper.PersonOrderMapper;
import com.omarbakr.foodfetch.model.OrderItem;
import com.omarbakr.foodfetch.model.OrderSession;
import com.omarbakr.foodfetch.model.PersonOrder;
import com.omarbakr.foodfetch.model.SessionStatus;
import com.omarbakr.foodfetch.repository.OrderSessionRepository;
import com.omarbakr.foodfetch.repository.PersonOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonOrderService {

    private final OrderSessionRepository orderSessionRepository;
    private final PersonOrderRepository personOrderRepository;

    public PersonOrderService(OrderSessionRepository orderSessionRepository,
                              PersonOrderRepository personOrderRepository) {
        this.orderSessionRepository = orderSessionRepository;
        this.personOrderRepository = personOrderRepository;
    }

    public PersonOrderResponse submitOrder(String sessionCode, PersonOrderRequest request) {
        OrderSession session = orderSessionRepository.findBySessionCode(sessionCode);
        if (session == null) throw new SessionNotFoundException(sessionCode);

        if (session.getStatus() == SessionStatus.CLOSED) {
            throw new SessionClosedException(sessionCode);
        }

        String personName = request.personName();
        if (personName == null || personName.isBlank()) {
            throw new MissingPersonNameException();
        }

        boolean alreadyExists = session.getPersonOrders().stream()
                .anyMatch(po -> po.getPersonName().equalsIgnoreCase(personName));
        if (alreadyExists) {
            throw new PersonOrderAlreadyExistsException(personName);
        }

        PersonOrder personOrder = PersonOrderMapper.toEntity(request);
        personOrder.setOrderSession(session);
        for (OrderItem item : personOrder.getOrderItems()) {
            item.setPersonOrder(personOrder);
        }

        return PersonOrderMapper.toResponse(personOrderRepository.save(personOrder));
    }
}

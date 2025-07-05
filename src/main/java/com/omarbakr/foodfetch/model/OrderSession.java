package com.omarbakr.foodfetch.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_sessions")
public class OrderSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String restaurant;

    private String notes;

    @Column(name = "extra_fees")
    private BigDecimal extraFees;

    @Column(name = "session_code", unique = true)
    private String sessionCode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "orderSession", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "orderSession-personOrders")
    private List<PersonOrder> personOrders = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionStatus status = SessionStatus.OPEN;

    public OrderSession() {}

    public OrderSession(String restaurant, String notes, BigDecimal extraFees, String sessionCode) {
        this.restaurant = restaurant;
        this.notes = notes;
        this.extraFees = extraFees;
        this.sessionCode = sessionCode;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getExtraFees() {
        return extraFees;
    }

    public void setExtraFees(BigDecimal extraFees) {
        this.extraFees = extraFees;
    }

    public String getSessionCode() {
        return sessionCode;
    }

    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
    }

    public List<PersonOrder> getPersonOrders() {
        return personOrders;
    }

    public void setPersonOrders(List<PersonOrder> personOrders) {
        this.personOrders = personOrders;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }
}
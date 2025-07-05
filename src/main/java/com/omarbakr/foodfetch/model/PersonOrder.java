package com.omarbakr.foodfetch.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person_orders")
public class PersonOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_session_id", nullable = false)
    @JsonBackReference(value = "orderSession-personOrders")
    private OrderSession orderSession;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-personOrders")
    private User user;

    @Column(name = "person_name", nullable = false)
    private String personName;

    private String notes;

    @OneToMany(mappedBy = "personOrder", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "personOrder-orderItems")
    private List<OrderItem> orderItems = new ArrayList<>();

    public PersonOrder() {}

    public PersonOrder(OrderSession orderSession, String personName, String notes) {
        this.orderSession = orderSession;
        this.personName = personName;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderSession getOrderSession() {
        return orderSession;
    }

    public void setOrderSession(OrderSession orderSession) {
        this.orderSession = orderSession;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}

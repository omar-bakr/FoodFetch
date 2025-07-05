package com.omarbakr.foodfetch.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_order_id", nullable = false)
    @JsonBackReference(value = "personOrder-orderItems")
    private PersonOrder personOrder;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(nullable = false)
    private BigDecimal itemPrice;

    @Column(nullable = false)
    private int quantity = 1;

    private String notes;

    public OrderItem() {
    }

    public OrderItem(PersonOrder personOrder, String itemName, BigDecimal price, String notes) {
        this.personOrder = personOrder;
        this.itemName = itemName;
        this.itemPrice = price;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonOrder getPersonOrder() {
        return personOrder;
    }

    public void setPersonOrder(PersonOrder personOrder) {
        this.personOrder = personOrder;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
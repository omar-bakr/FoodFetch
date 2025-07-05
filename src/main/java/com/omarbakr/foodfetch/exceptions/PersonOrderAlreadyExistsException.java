package com.omarbakr.foodfetch.exceptions;

public class PersonOrderAlreadyExistsException extends RuntimeException {
    public PersonOrderAlreadyExistsException(String personName) {
        super("Order already submitted by: " + personName);
    }
}
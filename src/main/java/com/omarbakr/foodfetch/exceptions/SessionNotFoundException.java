package com.omarbakr.foodfetch.exceptions;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(String sessionCode) {
        super("Order session not found for code: " + sessionCode);
    }
}
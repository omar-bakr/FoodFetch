package com.omarbakr.foodfetch.exceptions;

public class SessionAlreadyClosedException extends RuntimeException {
    public SessionAlreadyClosedException(String code) {
        super("Session is already closed: " + code);
    }
}
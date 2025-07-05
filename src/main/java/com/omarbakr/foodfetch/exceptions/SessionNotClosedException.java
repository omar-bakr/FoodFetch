package com.omarbakr.foodfetch.exceptions;

public class SessionNotClosedException extends RuntimeException {
    public SessionNotClosedException(String code) {
        super("Session is not closed yet: " + code);
    }
}
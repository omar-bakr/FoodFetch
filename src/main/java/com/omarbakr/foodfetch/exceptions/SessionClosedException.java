package com.omarbakr.foodfetch.exceptions;

public class SessionClosedException extends RuntimeException {
    public SessionClosedException(String sessionCode) {
        super("This session is closed. No more orders allowed. [code: " + sessionCode + "]");
    }
}
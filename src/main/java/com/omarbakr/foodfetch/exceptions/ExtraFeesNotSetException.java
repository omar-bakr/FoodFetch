package com.omarbakr.foodfetch.exceptions;

public class ExtraFeesNotSetException extends RuntimeException {
    public ExtraFeesNotSetException(String code) {
        super("Extra fees not set for session: " + code);
    }
}
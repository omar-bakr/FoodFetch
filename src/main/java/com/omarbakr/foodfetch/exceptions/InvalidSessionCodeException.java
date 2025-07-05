package com.omarbakr.foodfetch.exceptions;

public class InvalidSessionCodeException extends RuntimeException {
    public InvalidSessionCodeException(String code) {
        super("Invalid session code: " + code);
    }
}


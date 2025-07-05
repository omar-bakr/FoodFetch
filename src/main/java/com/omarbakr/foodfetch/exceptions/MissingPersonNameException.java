package com.omarbakr.foodfetch.exceptions;

public class MissingPersonNameException extends RuntimeException {
    public MissingPersonNameException() {
        super("Person name is required");
    }
}
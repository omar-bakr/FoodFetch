package com.omarbakr.foodfetch.exceptions;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(String name) {
        super("Restaurant not found: " + name);
    }
}

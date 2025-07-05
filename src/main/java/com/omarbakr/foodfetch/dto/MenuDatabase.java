package com.omarbakr.foodfetch.dto;

import java.util.List;

public class MenuDatabase {
    private List<RestaurantMenuResponse> restaurants;

    public List<RestaurantMenuResponse> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantMenuResponse> restaurants) {
        this.restaurants = restaurants;
    }
}

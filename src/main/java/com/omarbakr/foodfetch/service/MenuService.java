package com.omarbakr.foodfetch.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarbakr.foodfetch.dto.MenuDatabase;
import com.omarbakr.foodfetch.exceptions.RestaurantNotFoundException;
import com.omarbakr.foodfetch.dto.RestaurantMenuResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Service
public class MenuService {

    private static final String MENU_JSON_PATH = "/restaurants.json";
    private List<RestaurantMenuResponse> menus;

    @PostConstruct
    public void loadMenus() {
        try (InputStream is = getClass().getResourceAsStream(MENU_JSON_PATH)) {
            if (is == null) {
                throw new IllegalStateException("Menu JSON file not found at: " + MENU_JSON_PATH);
            }

            ObjectMapper mapper = new ObjectMapper();
            MenuDatabase data = mapper.readValue(is, MenuDatabase.class);
            this.menus = Collections.unmodifiableList(data.getRestaurants());

        } catch (Exception e) {
            throw new IllegalStateException("Failed to load menu JSON from: " + MENU_JSON_PATH, e);
        }
    }

    public RestaurantMenuResponse getMenuByName(String name) {
        return menus.stream()
                .filter(m -> m.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RestaurantNotFoundException(name));
    }

    public List<RestaurantMenuResponse> getAllMenus() {
        return menus;
    }
}

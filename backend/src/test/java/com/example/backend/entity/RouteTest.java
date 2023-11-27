package com.example.backend.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class RouteTest {

    private Route route;
    private HashMap<String, Stop> stops;
    private HashMap<String, RouteDirection> routeDirections;

    @BeforeEach
    void setUp() {
        // Arrange
        stops = new HashMap<>();
        Stop stop1 = new Stop("tag1", "Stop 1", 40.7128f, -74.0060f, new HashSet<>());
        Stop stop2 = new Stop("tag2", "Stop 2", 34.0522f, -118.2437f, new HashSet<>());
        stops.put("tag1", stop1);
        stops.put("tag2", stop2);

        routeDirections = new HashMap<>();
        route = new Route(stops, "routeTag", routeDirections);
    }


    @Test
    void getStops_ShouldReturnCorrectStops() {
        // Act
        HashMap<String, Stop> result = route.getStops();

        // Assert
        assertEquals(stops, result);
    }

    @Test
    void getRouteTag_ShouldReturnCorrectRouteTag() {
        // Act
        String result = route.getRouteTag();

        // Assert
        assertEquals("routeTag", result);
    }

    @Test
    void getRouteDirections_ShouldReturnCorrectRouteDirections() {
        // Act
        HashMap<String, RouteDirection> result = route.getRouteDirections();

        // Assert
        assertEquals(routeDirections, result);
    }
}

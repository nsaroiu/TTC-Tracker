package com.example.backend.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RouteDirectionTest {

    private RouteDirection routeDirection;

    @BeforeEach
    void setUp() {
        // Arrange
        ArrayList<String> stops = new ArrayList<>();
        stops.add("Stop1");
        stops.add("Stop2");
        String dirTag = "dirTag";
        String name = "Direction Name";
        routeDirection = new RouteDirection(stops, dirTag, name);
    }

    @Test
    void getStops_ShouldReturnCorrectStops() {
        // Act
        ArrayList<String> result = routeDirection.getStops();

        // Assert
        assertEquals(result.size(), routeDirection.getStops().size());
        assertTrue(result.contains("Stop1"));
        assertTrue(result.contains("Stop2"));
    }

    @Test
    void getDirTag_ShouldReturnCorrectDirTag() {
        // Act
        String result = routeDirection.getDirTag();

        // Assert
        assertEquals("dirTag", result);
    }

    @Test
    void getName_ShouldReturnCorrectName() {
        // Act
        String result = routeDirection.getName();

        // Assert
        assertEquals("Direction Name", result);
    }
}

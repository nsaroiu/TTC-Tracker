package com.example.backend.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class StopTest {

    private Stop stop;

    @BeforeEach
    void setUp() {
        // Arrange
        float latitude = 40.7128f;
        float longitude = -74.0060f;
        stop = new Stop("tag1", "Stop 1", latitude, longitude, new HashSet<>());
    }

    @Test
    void getTag_ShouldReturnCorrectTag() {
        // Act
        String result = stop.getTag();

        // Assert
        assertEquals("tag1", result);
    }

    @Test
    void getName_ShouldReturnCorrectName() {
        // Act
        String result = stop.getName();

        // Assert
        assertEquals("Stop 1", result);
    }

    @Test
    void getLocation_ShouldReturnCorrectLocation() {
        // Act
        Location result = stop.getLocation();

        // Assert
        assertEquals(40.7128f, result.getLatitude(), 0.0001);
        assertEquals(-74.0060f, result.getLongitude(), 0.0001);
    }

    @Test
    void getRouteTags_ShouldReturnCorrectRouteTags() {
        // Act
        HashSet<String> result = stop.getRouteTags();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}

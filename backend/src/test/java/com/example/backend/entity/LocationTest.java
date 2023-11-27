package com.example.backend.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    private Location location;

    @BeforeEach
    void setUp() {
        // Arrange
        location = new Location(40.7128f, -74.0060f);
    }

    @Test
    void getLatitude_ShouldReturnCorrectLatitude() {
        // Act
        float result = location.getLatitude();

        // Assert
        assertEquals(40.7128f, result, 0.0001);
    }

    @Test
    void getLongitude_ShouldReturnCorrectLongitude() {
        // Act
        float result = location.getLongitude();

        // Assert
        assertEquals(-74.0060f, result, 0.0001);
    }

    @Test
    void distanceTo_ShouldReturnCorrectDistance() {
        // Arrange
        Location otherLocation = new Location(34.0522f, -118.2437f);

        float expectedDistance = 3935.7463f;

        // Assert
        assertEquals(expectedDistance, location.distanceTo(otherLocation), 0.0001);
    }
}

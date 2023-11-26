package com.example.backend.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        // Arrange
        int id = 1;
        int speed = 50;
        float latitude = 40.7128f;
        float longitude = -74.0060f;
        int heading = 90;
        String directionTag = "EAST";
        vehicle = new Vehicle(id, speed, latitude, longitude, heading, directionTag);
    }

    @Test
    void getId_ShouldReturnCorrectId() {
        // Act
        int result = vehicle.getId();

        // Assert
        assertEquals(1, result);
    }

    @Test
    void getSpeed_ShouldReturnCorrectSpeed() {
        // Act
        int result = vehicle.getSpeed();

        // Assert
        assertEquals(50, result);
    }

    @Test
    void getLocation_ShouldReturnCorrectLocation() {
        // Act
        Location result = vehicle.getLocation();

        // Assert
        assertEquals(40.7128f, result.getLatitude(), 0.0001);
        assertEquals(-74.0060f, result.getLongitude(), 0.0001);
    }

    @Test
    void getHeading_ShouldReturnCorrectHeading() {
        // Act
        int result = vehicle.getHeading();

        // Assert
        assertEquals(90, result);
    }

    @Test
    void getDirectionTag_ShouldReturnCorrectDirectionTag() {
        // Act
        String result = vehicle.getDirectionTag();

        // Assert
        assertEquals("EAST", result);
    }
}

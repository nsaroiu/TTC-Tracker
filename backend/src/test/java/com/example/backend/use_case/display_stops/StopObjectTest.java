package com.example.backend.use_case.display_stops;

import com.example.backend.entity.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StopObjectTest {

    private StopObject stopObject;

    @BeforeEach
    void setUp() {
        // Initialize a StopObject for testing
        Location location = new Location(10.0f, 20.0f); // You may need to create a Location class constructor
        stopObject = new StopObject("Bus Stop 1", "TAG123", location);
    }

    @Test
    void getName() {
        // Test the getName() method
        assertEquals("Bus Stop 1", stopObject.getName());
    }

    @Test
    void getTag() {
        // Test the getTag() method
        assertEquals("TAG123", stopObject.getTag());
    }

    @Test
    void getLocation() {
        // Test the getLocation() method
        Location expectedLocation = new Location(10.0f, 20.0f);
        assertEquals(expectedLocation.getLatitude(), stopObject.getLocation().getLatitude());
        assertEquals(expectedLocation.getLongitude(), stopObject.getLocation().getLongitude());
    }
}

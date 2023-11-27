package com.example.backend.use_case.display_stops;

import com.example.backend.entity.Location;
import com.example.backend.entity.Stop;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StopsOutputDataTest {

    @Test
    void getStops_ShouldReturnCorrectStops() {
        // Arrange
        HashSet<Stop> stops = new HashSet<>();
        Stop stop1 = new Stop("tag1", "Stop 1", 40.7128f, -74.0060f, new HashSet<>());
        Stop stop2 = new Stop("tag2", "Stop 2", 34.0522f, -118.2437f, new HashSet<>());
        stops.add(stop1);
        stops.add(stop2);

        // Act
        StopsOutputData stopsOutputData = new StopsOutputData(stops);
        HashSet<StopObject> result = stopsOutputData.getStops();

        // Assert
        assertEquals(stops.size(), result.size());
    }
}

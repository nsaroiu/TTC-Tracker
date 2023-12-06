package com.example.backend.use_case.predictions.strategies;

import com.example.backend.entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class CalculateDistanceStrategyTest {

    @Test
    void execute_shouldReturnPredictions() {
        // Mock data setup
        ArrayList<Location> shape = new ArrayList<>();
        shape.add(new Location(0.0f, 0.0f));
        shape.add(new Location(1.0f, 1.0f));
        shape.add(new Location(2.0f, 2.0f));

        HashSet<String> routeTags = new HashSet<>();
        routeTags.add("route1");

        Stop stop = new Stop("stop1", "Stop 1", 1.5f, 1.5f, routeTags);

        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle(1, 20, 1.0f, 1.0f, 45, "direction1"));
        vehicles.add(new Vehicle(2, 25, 1.8f, 1.8f, 45, "direction1"));
        vehicles.add(new Vehicle(3, 18, 2.5f, 2.5f, 45, "direction1"));

        ArrayList<String> stopsList = new ArrayList<>();
        stopsList.add("stop1");

        RouteDirection routeDirection = new RouteDirection(stopsList, "direction1", "Direction 1");
        HashMap<String, RouteDirection> routeDirections = new HashMap<>();
        routeDirections.put("direction1", routeDirection);

        HashMap<String, Stop> stops = new HashMap<>();
        stops.put("stop1", stop);

        Route route = new Route(stops, "route1", routeDirections);

        // Create StrategyInputData using the StrategyInputDataBuilder
        StrategyInputData data = new StrategyInputDataBuilder()
                .setRoute(route)
                .setDirTag("direction1")
                .setStopTag("stop1")
                .setVehicles(vehicles)
                .setShape(shape)
                .setAvgSpeed(20.0f)
                .build();

        // Create an instance of the CalculateDistanceStrategy
        CalculateDistanceStrategy strategy = new CalculateDistanceStrategy();

        // Call the execute method and print the predictions
        ArrayList<Float> predictions = strategy.execute(data);

        // Verify the interaction
        assertNotNull(predictions);
        assertFalse(predictions.isEmpty());
        assertEquals(235.85162f, predictions.get(0), 0.001f); // Adjust the delta value based on precision
    }

}

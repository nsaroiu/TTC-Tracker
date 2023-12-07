package com.example.backend.use_case.vehicle_locations;

import com.example.backend.entity.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class VehicleLocationsOutputDataTest {

    @Test
    void testGetVehicles() {
        // Create sample vehicles
        Vehicle vehicle1 = new Vehicle(1, 60, 40.7128f, -74.0060f, 90, "EAST");
        Vehicle vehicle2 = new Vehicle(2, 55, 34.0522f, -118.2437f, 180, "SOUTH");
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        // Create VehicleLocationsOutputData instance
        VehicleLocationsOutputData outputData = new VehicleLocationsOutputData(vehicles);

        // Test getVehicles method
        assertNotNull(outputData.getVehicles());
        assertEquals(2, outputData.getVehicles().size());
        assertEquals(vehicle1, outputData.getVehicles().get(0));
        assertEquals(vehicle2, outputData.getVehicles().get(1));
    }
}

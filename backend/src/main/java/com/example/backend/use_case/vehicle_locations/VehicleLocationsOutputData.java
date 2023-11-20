package com.example.backend.use_case.vehicle_locations;

import com.example.backend.entity.Vehicle;

import java.util.ArrayList;

public class VehicleLocationsOutputData {

    ArrayList<Vehicle> vehicles;

    public VehicleLocationsOutputData(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }
}

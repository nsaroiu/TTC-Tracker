package com.example.backend.use_case.vehicle_locations;

import com.example.backend.entity.Vehicle;

import java.util.ArrayList;

public interface VehicleLocationsDataAccessInterface {

    /** Returns a list of vehicles for a given route.
     *
     * @param routeTag String representing route tag
     * @return ArrayList of Vehicle Objects that pertain to the route
     */
    ArrayList<Vehicle> getVehiclesByRouteTag(String routeTag);
}

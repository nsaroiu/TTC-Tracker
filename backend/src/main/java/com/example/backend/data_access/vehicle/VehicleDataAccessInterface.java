package com.example.backend.data_access.vehicle;

import com.example.backend.entity.Vehicle;

import java.util.ArrayList;

public interface VehicleDataAccessInterface {

    /** Returns a list of vehicles for a given route.
     *
     * @param routeTag String representing route tag
     * @return ArrayList of Vehicle Objects that pertain to the route
     */
    ArrayList<Vehicle> getVehiclesByRouteTag(String routeTag);

}

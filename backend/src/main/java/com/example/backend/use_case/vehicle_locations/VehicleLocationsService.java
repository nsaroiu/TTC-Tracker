package com.example.backend.use_case.vehicle_locations;

public interface VehicleLocationsService {

    VehicleLocationsOutputData execute(String routeTag, String dirTag);

}

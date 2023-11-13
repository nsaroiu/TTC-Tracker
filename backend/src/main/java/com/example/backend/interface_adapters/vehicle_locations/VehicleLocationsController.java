package com.example.backend.interface_adapters.vehicle_locations;

import com.example.backend.use_case.vehicle_locations.VehicleLocationsInputBoundary;
import com.example.backend.use_case.vehicle_locations.VehicleLocationsInputData;

public class VehicleLocationsController {
    final VehicleLocationsInputBoundary vehicleLocationsUseCaseInteractor;
    public VehicleLocationsController(VehicleLocationsInputBoundary vehicleLocationsUseCaseInteractor) {
        this.vehicleLocationsUseCaseInteractor = vehicleLocationsUseCaseInteractor;
    }

    public void execute() {
        vehicleLocationsUseCaseInteractor.execute(new VehicleLocationsInputData());
    }
}

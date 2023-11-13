package com.example.backend.use_case.vehicle_locations;

import com.example.backend.data_access.vehicle.VehicleDataAccessInterface;

public class VehicleLocationsInteractor implements VehicleLocationsInputBoundary {
    final VehicleLocationsOutputBoundary outputPresenter;
    final VehicleDataAccessInterface dataAccessObject;

    public VehicleLocationsInteractor(VehicleLocationsOutputBoundary outputPresenter, VehicleDataAccessInterface dataAccess) {
        this.outputPresenter = outputPresenter;
        this.dataAccessObject = dataAccess;
    }

    @Override
    public void execute(VehicleLocationsInputData inputData) {
    }
}

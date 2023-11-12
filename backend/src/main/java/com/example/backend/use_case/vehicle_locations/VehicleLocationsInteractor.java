package com.example.backend.use_case.vehicle_locations;

public class VehicleLocationsInteractor implements VehicleLocationsInputBoundary {
    final VehicleLocationsOutputBoundary outputPresenter;
    final VehicleLocationsDataAccessInterface dataAccess;

    public VehicleLocationsInteractor(VehicleLocationsOutputBoundary outputPresenter, VehicleLocationsDataAccessInterface dataAccess) {
        this.outputPresenter = outputPresenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(VehicleLocationsInputData inputData) {
    }
}

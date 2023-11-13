package com.example.backend.use_case.vehicle_locations;

public class VehicleLocationsInteractor implements VehicleLocationsInputBoundary {
    final VehicleLocationsOutputBoundary outputPresenter;
    final VehicleLocationsDataAccessInterface dataAccessObject;

    public VehicleLocationsInteractor(VehicleLocationsOutputBoundary outputPresenter, VehicleLocationsDataAccessInterface dataAccess) {
        this.outputPresenter = outputPresenter;
        this.dataAccessObject = dataAccess;
    }

    @Override
    public void execute(VehicleLocationsInputData inputData) {
    }
}

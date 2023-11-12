package com.example.backend.interface_adapters.vehicle_locations;

import com.example.backend.interface_adapters.ViewManagerModel;
import com.example.backend.use_case.vehicle_locations.VehicleLocationsOutputBoundary;
import com.example.backend.use_case.vehicle_locations.VehicleLocationsOutputData;

public class VehicleLocationsPresenter implements VehicleLocationsOutputBoundary {

    private final VehicleLocationsViewModel vehicleLocationsViewModel;
    private ViewManagerModel viewManagerModel;

    public VehicleLocationsPresenter(VehicleLocationsViewModel vehicleLocationsViewModel, ViewManagerModel viewManagerModel){
        this.vehicleLocationsViewModel = vehicleLocationsViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(VehicleLocationsOutputData vehicleLocationsOutputData) {
        VehicleLocationsState vehicleLocationsState = vehicleLocationsViewModel.getState();
        this.vehicleLocationsViewModel.setState(vehicleLocationsState);
        vehicleLocationsViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(vehicleLocationsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        VehicleLocationsState vehicleLocationsState = vehicleLocationsViewModel.getState();
        vehicleLocationsViewModel.firePropertyChanged();
    }
}

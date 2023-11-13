package com.example.backend.use_case.route_details;

import com.example.backend.data_access.route.RouteDataAccessInterface;

public class RouteDetailsInteractor implements RouteDetailsInputBoundary {
    final RouteDetailsOutputBoundary outputPresenter;
    final RouteDataAccessInterface dataAccessObject;

    public RouteDetailsInteractor(RouteDetailsOutputBoundary outputPresenter, RouteDataAccessInterface dataAccess) {
        this.outputPresenter = outputPresenter;
        this.dataAccessObject = dataAccess;
    }

    @Override
    public void execute(RouteDetailsInputData inputData) {
    }
}

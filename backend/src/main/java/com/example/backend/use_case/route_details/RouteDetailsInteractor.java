package com.example.backend.use_case.route_details;

public class RouteDetailsInteractor implements RouteDetailsInputBoundary {
    final RouteDetailsOutputBoundary outputPresenter;
    final RouteDetailsDataAccessInterface dataAccessObject;

    public RouteDetailsInteractor(RouteDetailsOutputBoundary outputPresenter, RouteDetailsDataAccessInterface dataAccess) {
        this.outputPresenter = outputPresenter;
        this.dataAccessObject = dataAccess;
    }

    @Override
    public void execute(RouteDetailsInputData inputData) {
    }
}

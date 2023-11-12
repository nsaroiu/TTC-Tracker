package com.example.backend.use_case.route_details;

public class RouteDetailsInteractor implements RouteDetailsInputBoundary {
    final RouteDetailsOutputBoundary outputPresenter;
    final RouteDetailsDataAccessInterface dataAccess;

    public RouteDetailsInteractor(RouteDetailsOutputBoundary outputPresenter, RouteDetailsDataAccessInterface dataAccess) {
        this.outputPresenter = outputPresenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(RouteDetailsInputData inputData) {
    }
}

package com.example.backend.use_case.route_details;

public interface RouteDetailsOutputBoundary {
    void prepareSuccessView(RouteDetailsOutputData data);

    void prepareFailView(String error);
}

package com.example.backend.use_case.display_stops;

public interface StopsOutputBoundary {
    void prepareSuccessView(StopsOutputData stopsOutputData);

    void prepareFailView(String error);
}

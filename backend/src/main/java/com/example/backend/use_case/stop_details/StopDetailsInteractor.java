package com.example.backend.use_case.stop_details;

public class StopDetailsInteractor implements StopDetailsInputBoundary {
    final StopDetailsOutputBoundary outputPresenter;
    final StopDetailsDataAccessInterface dataAccess;

    public StopDetailsInteractor(StopDetailsOutputBoundary outputPresenter, StopDetailsDataAccessInterface dataAccess) {
        this.outputPresenter = outputPresenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(StopDetailsInputData inputData) {
    }
}

package com.example.backend.use_case.stop_details;

import com.example.backend.data_access.stop.StopDataAccessInterface;

public class StopDetailsInteractor implements StopDetailsInputBoundary {
    final StopDetailsOutputBoundary outputPresenter;
    final StopDataAccessInterface dataAccessObject;

    public StopDetailsInteractor(StopDetailsOutputBoundary outputPresenter, StopDataAccessInterface dataAccess) {
        this.outputPresenter = outputPresenter;
        this.dataAccessObject = dataAccess;
    }

    @Override
    public void execute(StopDetailsInputData inputData) {
    }
}

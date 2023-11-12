package com.example.backend.use_case.predictions;

public class PredictionsInteractor implements PredictionsInputBoundary {
    final PredictionsOutputBoundary outputPresenter;
    final PredictionsDataAccessInterface dataAccess;

    public PredictionsInteractor(PredictionsOutputBoundary outputPresenter, PredictionsDataAccessInterface dataAccess) {
        this.outputPresenter = outputPresenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(PredictionsInputData inputData) {
    }
}

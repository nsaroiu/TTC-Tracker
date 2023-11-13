package com.example.backend.use_case.predictions;

public class PredictionsInteractor implements PredictionsInputBoundary {
    final PredictionsOutputBoundary outputPresenter;
    final PredictionsDataAccessInterface dataAccessObject;

    public PredictionsInteractor(PredictionsOutputBoundary outputPresenter, PredictionsDataAccessInterface dataAccess) {
        this.outputPresenter = outputPresenter;
        this.dataAccessObject = dataAccess;
    }

    @Override
    public void execute(PredictionsInputData inputData) {
    }
}

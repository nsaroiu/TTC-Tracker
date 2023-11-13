package com.example.backend.interface_adapters.predictions;

import com.example.backend.use_case.predictions.PredictionsInputBoundary;
import com.example.backend.use_case.predictions.PredictionsInputData;

public class PredictionsController {
    final PredictionsInputBoundary predictionsUseCaseInteractor;
    public PredictionsController(PredictionsInputBoundary predictionsUseCaseInteractor) {
        this.predictionsUseCaseInteractor = predictionsUseCaseInteractor;
    }

    public void execute() {
        predictionsUseCaseInteractor.execute(new PredictionsInputData());
    }
}

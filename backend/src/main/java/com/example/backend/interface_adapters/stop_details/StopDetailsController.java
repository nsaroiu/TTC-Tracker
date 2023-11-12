package com.example.backend.interface_adapters.stop_details;

import com.example.backend.use_case.stop_details.StopDetailsInputBoundary;
import com.example.backend.use_case.stop_details.StopDetailsInputData;

public class StopDetailsController {
    final StopDetailsInputBoundary stopDetailsUseCaseInteractor;
    public StopDetailsController(StopDetailsInputBoundary stopDetailsUseCaseInteractor) {
        this.stopDetailsUseCaseInteractor = stopDetailsUseCaseInteractor;
    }

    public void execute() {
        stopDetailsUseCaseInteractor.execute(new StopDetailsInputData());
    }
}

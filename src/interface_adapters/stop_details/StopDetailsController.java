package interface_adapters.stop_details;

import use_case.stop_details.StopDetailsInputBoundary;
import use_case.stop_details.StopDetailsInputData;

public class StopDetailsController {
    final StopDetailsInputBoundary stopDetailsUseCaseInteractor;
    public StopDetailsController(StopDetailsInputBoundary stopDetailsUseCaseInteractor) {
        this.stopDetailsUseCaseInteractor = stopDetailsUseCaseInteractor;
    }

    public void execute() {
        stopDetailsUseCaseInteractor.execute(new StopDetailsInputData());
    }
}

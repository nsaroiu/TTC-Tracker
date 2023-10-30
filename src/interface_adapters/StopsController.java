package interface_adapters;

import use_case.StopsInputBoundary;

public class StopsController {
    final StopsInputBoundary stopsUseCaseInteractor;
    public StopsController(StopsInputBoundary stopsUseCaseInteractor) {
        this.stopsUseCaseInteractor = stopsUseCaseInteractor;
    }

    public void execute() {
        //There is no input data for this case
        stopsUseCaseInteractor.execute();
    }
}

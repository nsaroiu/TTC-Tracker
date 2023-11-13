package interface_adapters.display_stops;

import use_case.display_stops.StopsInputBoundary;

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
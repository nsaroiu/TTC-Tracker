package interface_adapters.route_details;

import use_case.route_details.RouteDetailsInputBoundary;
import use_case.route_details.RouteDetailsInputData;

public class RouteDetailsController {
    final RouteDetailsInputBoundary routeDetailsUseCaseInteractor;
    public RouteDetailsController(RouteDetailsInputBoundary routeDetailsUseCaseInteractor) {
        this.routeDetailsUseCaseInteractor = routeDetailsUseCaseInteractor;
    }

    public void execute() {
        routeDetailsUseCaseInteractor.execute(new RouteDetailsInputData());
    }
}

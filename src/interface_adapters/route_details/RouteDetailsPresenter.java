package interface_adapters.route_details;

import interface_adapters.ViewManagerModel;
import use_case.route_details.RouteDetailsOutputBoundary;
import use_case.route_details.RouteDetailsOutputData;

public class RouteDetailsPresenter implements RouteDetailsOutputBoundary {

    private final RouteDetailsViewModel routeDetailsViewModel;
    private ViewManagerModel viewManagerModel;

    public RouteDetailsPresenter(RouteDetailsViewModel routeDetailsViewModel, ViewManagerModel viewManagerModel){
        this.routeDetailsViewModel = routeDetailsViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(RouteDetailsOutputData routeDetailsOutputData) {
        RouteDetailsState routeDetailsState = routeDetailsViewModel.getState();
        this.routeDetailsViewModel.setState(routeDetailsState);
        routeDetailsViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(routeDetailsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        RouteDetailsState routeDetailsState = routeDetailsViewModel.getState();
        routeDetailsViewModel.firePropertyChanged();
    }
}

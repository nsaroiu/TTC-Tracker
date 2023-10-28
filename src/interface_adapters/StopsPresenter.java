package interface_adapters;

import use_case.StopsOutputBoundary;
import use_case.StopsOutputData;

public class StopsPresenter implements StopsOutputBoundary {

    private final StopsViewModel stopsViewModel;
    private ViewManagerModel viewManagerModel;

    public StopsPresenter(StopsViewModel stopsViewModel, ViewManagerModel viewManagerModel){
        this.stopsViewModel = stopsViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(StopsOutputData stopsOutputData) {
        StopsState stopsState = stopsViewModel.getState();
        this.stopsViewModel.setState(stopsState);
        stopsViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(stopsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        StopsState stopsState = stopsViewModel.getState();
        stopsState.setStopsError(error);
        stopsViewModel.firePropertyChanged();
    }
}

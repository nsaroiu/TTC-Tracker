package com.example.backend.interface_adapters.stop_details;

import com.example.backend.interface_adapters.ViewManagerModel;
import com.example.backend.use_case.stop_details.StopDetailsOutputBoundary;
import com.example.backend.use_case.stop_details.StopDetailsOutputData;

public class StopDetailsPresenter implements StopDetailsOutputBoundary {

    private final StopDetailsViewModel stopDetailsViewModel;
    private ViewManagerModel viewManagerModel;

    public StopDetailsPresenter(StopDetailsViewModel stopDetailsViewModel, ViewManagerModel viewManagerModel){
        this.stopDetailsViewModel = stopDetailsViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(StopDetailsOutputData stopDetailsOutputData) {
        StopDetailsState stopDetailsState = stopDetailsViewModel.getState();
        this.stopDetailsViewModel.setState(stopDetailsState);
        stopDetailsViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(stopDetailsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        StopDetailsState stopDetailsState = stopDetailsViewModel.getState();
        stopDetailsViewModel.firePropertyChanged();
    }
}

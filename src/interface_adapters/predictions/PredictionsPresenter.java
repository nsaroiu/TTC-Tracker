package interface_adapters.predictions;

import interface_adapters.ViewManagerModel;
import use_case.predictions.PredictionsOutputBoundary;
import use_case.predictions.PredictionsOutputData;

public class PredictionsPresenter implements PredictionsOutputBoundary {

    private final PredictionsViewModel predictionsViewModel;
    private ViewManagerModel viewManagerModel;

    public PredictionsPresenter(PredictionsViewModel predictionsViewModel, ViewManagerModel viewManagerModel){
        this.predictionsViewModel = predictionsViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(PredictionsOutputData predictionsOutputData) {
        PredictionsState predictionsState = predictionsViewModel.getState();
        this.predictionsViewModel.setState(predictionsState);
        predictionsViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(predictionsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        PredictionsState predictionsState = predictionsViewModel.getState();
        predictionsViewModel.firePropertyChanged();
    }
}

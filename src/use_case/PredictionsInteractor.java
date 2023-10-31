package use_case;

import data_access.PredictionsDataAccessInterface;
import data_access.StopDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class PredictionsInteractor implements PredictionsInputBoundary{
    final PredictionsDataAccessInterface predictionsDataAccessInterface;
    final PredictionsOutputBoundary predictionsPresenter;

    public PredictionsInteractor(PredictionsDataAccessInterface predictionsDataAccessInterface, PredictionsOutputBoundary predictionsPresenter){
        this.predictionsDataAccessInterface = predictionsDataAccessInterface;
        this.predictionsPresenter = predictionsPresenter;
    }
    @Override
    public void execute(PredictionsInputData predictionsInputData) {
        String stopTag = predictionsInputData.getStop();
        HashMap<String, ArrayList<Integer>> directionToArrivals = predictionsDataAccessInterface.getAllDirectionsAndArrivals(stopTag);
        if (directionToArrivals.isEmpty()){
            predictionsPresenter.prepareFailView("Could not find any predictions.");
        }
        else{
            PredictionsOutputData predictionsOutputData = new PredictionsOutputData(directionToArrivals);
            predictionsPresenter.prepareSuccessView(predictionsOutputData);
        }
    }
}

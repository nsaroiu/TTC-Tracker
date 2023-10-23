package use_case;

import data_access.StopDataAccessInterface;
import entity.Stop;

import java.util.HashMap;
import java.util.HashSet;

public class StopsInteractor implements StopsInputBoundary {
    final StopDataAccessInterface stopDataAccessInterface;
    final StopsOutputBoundary stopPresenter;
    public StopsInteractor(StopDataAccessInterface stopDataAccessInterface, StopsOutputBoundary stopPresenter){
        this.stopDataAccessInterface = stopDataAccessInterface;
        this.stopPresenter = stopPresenter;

    }

    @Override
    public void execute() {
        HashSet<Stop> allStops = stopDataAccessInterface.getAllStops();
        if (allStops.isEmpty()){
            stopPresenter.prepareFailView("Could not fetch any stops.");
        }
        else {
            StopsOutputData stopsOutputData = new StopsOutputData(allStops);
            stopPresenter.prepareSuccessView(stopsOutputData);
        }
        


    }
}

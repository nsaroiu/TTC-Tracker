package com.example.backend.use_case.display_stops;

import com.example.backend.data_access.stop.StopDataAccessInterface;
import com.example.backend.entity.Location;

import java.util.HashMap;

public class StopsInteractor implements StopsInputBoundary {
    final StopDataAccessInterface stopDataAccessInterface;
    final StopsOutputBoundary stopPresenter;
    public StopsInteractor(StopDataAccessInterface stopDataAccessInterface, StopsOutputBoundary stopPresenter){
        this.stopDataAccessInterface = stopDataAccessInterface;
        this.stopPresenter = stopPresenter;

    }

    @Override
    public void execute() {
        HashMap<String, Location> allStops = stopDataAccessInterface.getAllStopTagsAndLocations();
        if (allStops.isEmpty()){
            stopPresenter.prepareFailView("Could not fetch any stops.");
        }
        else {
            StopsOutputData stopsOutputData = new StopsOutputData(allStops);
            stopPresenter.prepareSuccessView(stopsOutputData);

        }
        


    }
}

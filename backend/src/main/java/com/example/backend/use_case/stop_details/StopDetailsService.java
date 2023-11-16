package com.example.backend.use_case.stop_details;

import com.example.backend.data_access.stop.StopDataAccessInterface;
import com.example.backend.entity.Location;
import com.example.backend.entity.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class StopDetailsService {
    @Autowired
    private StopDataAccessInterface stopDataAccessObject;

    public StopDetailsOutputData execute(StopDetailsInputData inputData) throws Exception {
        String stopTag = inputData.getStopTag();
        HashSet<Stop> stopsSet = stopDataAccessObject.getAllStops();
        for (Stop stop:
             stopsSet) {
            if (stopTag.equals(stop.getTag())){
                //Change getTag to getName once getName is implemented
                StopDetailsOutputData outputData = new StopDetailsOutputData(stop.getTag(),stop.getRoutes());
                return outputData;
            }
        }
        //You are not supposed to reach this.
        throw new StopNotFoundException("Stop tag not found");
    }
}

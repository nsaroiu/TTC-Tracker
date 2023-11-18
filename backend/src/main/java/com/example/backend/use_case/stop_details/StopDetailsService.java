package com.example.backend.use_case.stop_details;

import com.example.backend.data_access.route.RouteDataAccessInterface;
import com.example.backend.data_access.stop.StopDataAccessInterface;
import com.example.backend.entity.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * This is the service class for the stop details use case.
 */
@Service
public class StopDetailsService {
    /**
     * Instance variables:
    *  - routeDataAccessObject: the data access object used to access data about routes (such as routeTags)
    */
    @Autowired
    private RouteDataAccessInterface routeDataAccessObject;
    @Autowired
    private StopDataAccessInterface stopDataAccessObject;

//    public StopDetailsService(StopDataAccessInterface stopDataAccessObject, RouteDataAccessInterface routeDataAccessObject){
//        this.stopDataAccessObject = stopDataAccessObject;
//        this.routeDataAccessObject = routeDataAccessObject;
//    }

    /**
     * Given an input data, extracts the stopTag and fetches the name of the corresponding stop.
     * Finds the route tags of all the routes that pass through the stop and puts them into a set called routeTags.
     * Creates the output data containing the stopName and the routeTags, and returns it.
     */
    public StopDetailsOutputData execute(StopDetailsInputData inputData){
        //Extract the stop tag
        String stopTag = inputData.getStopTag();
        //TODO: Replace this part with the actual name once names for Stops are implemented.
        //TODO: Introduce a way to get one stop from all stops while doing that
        HashSet<Stop> allStops = stopDataAccessObject.getAllStops();
        String stopName = "Empty";
        for (Stop stop : allStops){
            if (stopTag.equals(stop.getTag())){
                stopName = stop.getName();
            }
        }
        if (stopName.equals("Empty")){
            stopName = stopTag;
        }
        //Use the stopTag to get the routeTags
        HashSet<String> routeTags = routeDataAccessObject.getRouteTagsByStopTag(stopTag);
        //Initialize new outputData with the stopName and the routeTags
        StopDetailsOutputData outputData = new StopDetailsOutputData(stopName, routeTags);
        //Return the outputData
        return outputData;
    }
}

package com.example.backend.use_case.stop_details;

import com.example.backend.data_access.route.RouteDataAccessInterface;
import com.example.backend.data_access.stop.StopDataAccessInterface;
import com.example.backend.entity.RouteDirection;
import com.example.backend.entity.Stop;
import com.example.backend.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.service.invoker.HttpServiceArgumentResolver;

import java.util.ArrayList;
import java.util.HashMap;
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
        //Get a set of all stops
        HashSet<Stop> allStops = stopDataAccessObject.getAllStops();
        //Initialize the stop name
        String stopName = "Empty";
        for (Stop stop : allStops){ // Check each stop
            // If the stop tags match, get the name
            if (stopTag.equals(stop.getTag())){
                stopName = stop.getName();
            }
        }
        // If still empty, make the stopTag the name (this will never be satisfied, but is included to be safe)
        if (stopName.equals("Empty")){
            stopName = stopTag;
        }
        //Initialize the mapping (maps routeTags to routeName and dirTags to dirName)
        HashMap<String, ArrayList<Object>> routeTagsToWrapper = new HashMap<>();
        //Use the stopTag to get the routeTags
        HashSet<String> routeTags = routeDataAccessObject.getRouteTagsByStopTag(stopTag);
        for (String routeTag : routeTags){
            //Use the routeTag to get the route
            Route route = routeDataAccessObject.getRouteByRouteTag(routeTag);
            //Use the route to get the routeName
            //TODO: Replace this with route.getName() once implemented
            String routeName = route.getRouteTag();
            //Use the route to get its direction tags
            HashMap<String, RouteDirection> directions = route.getRouteDirections();
            HashSet<String> dirTags = new HashSet<>();
            dirTags.addAll(directions.keySet());
            //Create the wrapper that contains routeName, and dirTags to dirNames
            ArrayList<Object> wrapper = new ArrayList<>();
            wrapper.add(routeName);
            HashMap<String, String> dirTagsToNames = new HashMap<>();
            for (String dirTag : dirTags){
               RouteDirection direction = directions.get(dirTag);
               String dirName = direction.getName();
               dirTagsToNames.put(dirTag, dirName);
            }
            wrapper.add(dirTagsToNames);
            //Map the routeTag to the wrapper (routeName, and dirTags to dirNames)
            routeTagsToWrapper.put(routeTag, wrapper);
        }

        //Initialize new outputData with the stopName and the routeTagsToWrapper
        StopDetailsOutputData outputData = new StopDetailsOutputData(stopName, routeTagsToWrapper);
        //Return the outputData
        return outputData;
    }
}

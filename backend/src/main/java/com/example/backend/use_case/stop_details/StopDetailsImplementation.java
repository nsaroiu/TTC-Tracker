package com.example.backend.use_case.stop_details;

import com.example.backend.data_access.route.RouteDataAccessInterface;
import com.example.backend.data_access.stop.StopDataAccessInterface;
import com.example.backend.entity.RouteDirection;
import com.example.backend.entity.Stop;
import com.example.backend.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.HashSet;

/**
 * This is the implementation class for the stop details use case. It implements StopDetailsService.
 */
@Service
public class StopDetailsImplementation implements StopDetailsService {
    /**
     * Instance variables:
     *  - routeDataAccessObject: the data access object used to access data about routes (such as routeTags)
     *  - stopDataAccessObject: the data access object used to access data about stops (such as stopName)
     */
    @Autowired
    private RouteDataAccessInterface routeDataAccessObject;
    @Autowired
    private StopDataAccessInterface stopDataAccessObject;

    /**
     * Given an input data, extracts the stopTag and a mapping of routeTags to a mapping
     * of direction tags to direction names, returns them as the output data.
     */
    public StopDetailsOutputData execute(String stopTag){
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
        //Initialize the mapping (maps routeTags to a mapping of dirTags to dirName)
        HashMap<String, HashMap<String, String>> routeTagsToDir = new HashMap<>();
        //Use the stopTag to get the routeTags
        HashSet<String> routeTags = routeDataAccessObject.getRouteTagsByStopTag(stopTag);
        for (String routeTag : routeTags){
            //Use the routeTag to get the route
            Route route = routeDataAccessObject.getRouteByRouteTag(routeTag);
            //Use the route to get its direction tags
            HashMap<String, RouteDirection> directions = route.getRouteDirections();
            HashSet<String> dirTags = new HashSet<>();
            dirTags.addAll(directions.keySet());
            HashMap<String, String> dirTagsToNames = new HashMap<>();
            //Extract direction tag and direction name
            for (String dirTag : dirTags){
                RouteDirection direction = directions.get(dirTag);
                String dirName = direction.getName();
                dirTagsToNames.put(dirTag, dirName);
            }

            //Map the routeTag to the mapping of direction tags to direction names
            routeTagsToDir.put(routeTag, dirTagsToNames);
        }

        //Initialize new outputData with the stopName and the route tags mapped to a mapping of
        // direction tags to direction names
        StopDetailsOutputData outputData = new StopDetailsOutputData(stopName, routeTagsToDir);
        //Return the outputData
        return outputData;
    }
}

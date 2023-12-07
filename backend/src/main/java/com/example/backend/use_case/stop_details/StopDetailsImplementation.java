package com.example.backend.use_case.stop_details;

import com.example.backend.entity.Route;
import com.example.backend.entity.RouteDirection;
import com.example.backend.entity.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Implementation class for the stop details use case, implementing StopDetailsService.
 */
@Service
public class StopDetailsImplementation implements StopDetailsService {
    /**
     * Data access object for routes, used to access data about routes such as routeTags.
     */
    @Autowired
    private StopDetailsDataAccessInterface stopDetailsDataAccessObject;

    /**
     * Given a stopTag, return StopDetailsOutputData.
     * @param stopTag The identifier for the stop.
     * @return The output data containing a mapping of routeTags to a mapping of direction tags to direction names
     */
    public StopDetailsOutputData execute(String stopTag){

        //Get a set of all stops
        HashSet<Stop> allStops = stopDetailsDataAccessObject.getAllStops();

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
        HashSet<String> routeTags = stopDetailsDataAccessObject.getRouteTagsByStopTag(stopTag);
        for (String routeTag : routeTags){

            //Use the routeTag to get the route
            Route route = stopDetailsDataAccessObject.getRouteByRouteTag(routeTag);

            //Use the route to get its direction tags
            HashMap<String, RouteDirection> directions = route.getRouteDirections();
            HashSet<String> dirTags = new HashSet<>(directions.keySet());
            HashMap<String, String> dirTagsToNames = new HashMap<>();

            //Extract direction tag and direction name
            for (String dirTag : dirTags){
                RouteDirection direction = directions.get(dirTag);
                //Look at every stopTag this direction serves
                ArrayList<String> dirStopTags = direction.getStops();

                //Only add the dirTag to dirName if the direction serves our stopTag
                if (dirStopTags.contains(stopTag)) {
                    String dirName = direction.getName();
                    dirTagsToNames.put(dirTag, dirName);
                }
            }

            //Only map the routeTag to the mapping of direction tags to direction names
            // if the mapping of direction tags to direction names is not empty
            if (!dirTagsToNames.isEmpty()){
                routeTagsToDir.put(routeTag, dirTagsToNames);
            }

        }

        //Initialize new outputData with the stopName and the route tags mapped to a mapping of
        // direction tags to direction names and return it
        return new StopDetailsOutputData(stopName, routeTagsToDir);
    }
}

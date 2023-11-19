package com.example.backend.use_case.stop_details;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
/**
 * This is the output data class for the stop details use case.
 */
public class StopDetailsOutputData {
    /**
     * Instance variables:
     * - stopName: the name of the stop
     * - routeTagsToWrapper: maps route tags to route name, and direction tags mapped to direction names
     */
    private String stopName;
    private HashMap<String, ArrayList<Object>> routeTagsToWrapper;
    /**
     * Creates a new instance of StopDetailsOutputData
     */
    public StopDetailsOutputData(String stopName, HashMap<String, ArrayList<Object>> routeTagsToWrapper) {
        this.stopName = stopName;
        this.routeTagsToWrapper = routeTagsToWrapper;
    }
    /**
    * Returns the stopName
    */
    public String getName(){return this.stopName;}
    /**
     * Returns the mapping from route tags to route name and direction tags
     */
    public HashMap<String, ArrayList<Object>> getRouteTagsToWrapper(){return this.routeTagsToWrapper;}

    //public HashSet<String> getRouteTags(){return this.routeTags;}
}
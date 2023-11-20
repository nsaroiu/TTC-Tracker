package com.example.backend.use_case.stop_details;

import java.util.HashMap;

/**
 * This is the output data class for the stop details use case.
 */
public class StopDetailsOutputData {
    /**
     * Instance variables:
     * - stopName: the name of the stop
     * - routeTagsToDir: maps route tags to a mapping of direction tags to direction names
     */
    private String stopName;
    private HashMap<String, HashMap<String, String>> routeTagsToDir;
    /**
     * Creates a new instance of StopDetailsOutputData
     */
    public StopDetailsOutputData(String stopName, HashMap<String, HashMap<String, String>> routeTagsToDir) {
        this.stopName = stopName;
        this.routeTagsToDir = routeTagsToDir;
    }
    /**
    * Returns the stopName
    */
    public String getStopName(){return this.stopName;}
    /**
     * Returns the mapping from route tags to a mapping of direction tags to direction names
     */
    public HashMap<String, HashMap<String, String>> getRouteTagsToDir(){return this.routeTagsToDir;}
}
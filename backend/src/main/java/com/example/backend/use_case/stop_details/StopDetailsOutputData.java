package com.example.backend.use_case.stop_details;

import java.util.HashMap;

/**
 * Output data class for the stop details use case.
 */
public class StopDetailsOutputData {
    /**
     * The name of the stop.
     */
    private String stopName;

    /**
     * Maps route tags to a mapping of direction tags to direction names.
     */
    private HashMap<String, HashMap<String, String>> routeTagsToDir;

    /**
     * Creates a new instance of StopDetailsOutputData
     *
     * @param stopName      The name of the stop.
     * @param routeTagsToDir Mapping of route tags to a mapping of direction tags to direction names.
     */

    public StopDetailsOutputData(String stopName, HashMap<String, HashMap<String, String>> routeTagsToDir) {
        this.stopName = stopName;
        this.routeTagsToDir = routeTagsToDir;
    }

    /**
     * Gets the name of the stop.
     *
     * @return The name of the stop.
     */
    public String getStopName(){return this.stopName;}

    /**
     * Gets the mapping from route tags to a mapping of direction tags to direction names.
     *
     * @return Mapping of route tags to a mapping of direction tags to direction names.
     */
    public HashMap<String, HashMap<String, String>> getRouteTagsToDir(){return this.routeTagsToDir;}
}
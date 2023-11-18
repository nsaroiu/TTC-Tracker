package com.example.backend.use_case.stop_details;

import java.util.HashSet;
/**
 * This is the output data class for the stop details use case.
 */
public class StopDetailsOutputData {
    /**
     * Instance variables:
     * - stopName: the name of the stop
     * - routeTags: a set of all the routeTags of routes going through this stop
     */
    private String stopName;
    private HashSet<String> routeTags;
    //private HashMap<String, ArrayList<>>?
    /**
     * Creates a new instance of StopDetailsOutputData
     */
    public StopDetailsOutputData(String stopName, HashSet<String> routeTags) {
        this.stopName = stopName;
        this.routeTags = routeTags;
    }
    /**
    * Returns the stopName
    */
    public String getName(){return this.stopName;}
    /**
     * Returns the routeTags
     */
    public HashSet<String> getRouteTags(){return this.routeTags;}
}

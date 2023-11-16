package com.example.backend.use_case.stop_details;

import com.example.backend.entity.Route;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StopDetailsOutputData {
    /**
     * stopname: the name of the stop
     */
    private String stopName;
    /*
     * routes: maps routes to a list containing route shape and route tag
     * */
    private HashMap<Route, ArrayList<Object>> routes;
    public StopDetailsOutputData(String stopName, HashMap<Route, ArrayList<Object>> routes) {
        this.stopName = stopName;
        this.routes = routes;
    }
/**
 * Returns the stopName
 * */
    public String getName(){return this.stopName;}
    /**
     * Returns the Routes mapped to their Shapes and RouteTags.
     * */
    public HashMap<Route, ArrayList<Object>> getRoutes(){return this.routes;}
}

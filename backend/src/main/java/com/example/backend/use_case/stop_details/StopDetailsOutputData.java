package com.example.backend.use_case.stop_details;

import com.example.backend.entity.Route;

import java.util.HashSet;

public class StopDetailsOutputData {
    private String stopName;
    private HashSet<String> routes;
    public StopDetailsOutputData(String stopName, HashSet<String> routes) {
        this.stopName = stopName;
        this.routes = routes;
    }

    public String getName(){return this.stopName;}
    public HashSet<String> getRoutes(){return this.routes;}
}

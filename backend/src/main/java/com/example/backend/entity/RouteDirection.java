package com.example.backend.entity;

import java.util.ArrayList;

public class RouteDirection {

    ArrayList<String> stops;
    String dirTag;
    String name;

    public RouteDirection(ArrayList<String> stops, String dirTag, String name) {
        this.dirTag = dirTag;
        this.name = name;
        this.stops = stops;
    }

    public ArrayList<String> getStops() { return stops; }

    public String getDirTag() { return dirTag; }

    public String getName() { return name; }
}

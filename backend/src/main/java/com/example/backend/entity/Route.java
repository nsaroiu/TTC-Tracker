package com.example.backend.entity;

import java.util.HashMap;

public class Route {

    HashMap<String, Stop> stops;
    String routeTag;
    HashMap<String, RouteDirection> routeDirections;

    public Route(HashMap<String, Stop> stops, String routeTag, HashMap<String, RouteDirection> routeDirections) {
        this.routeTag = routeTag;
        this.stops = stops;
        this.routeDirections = routeDirections;
    }

    public HashMap<String, Stop> getStops() {
        return stops;
    }

    public String getRouteTag() {
        return routeTag;
    }

    public HashMap<String, RouteDirection> getRouteDirections() { return routeDirections; }
}

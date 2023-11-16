package com.example.backend.entity;

import java.util.HashMap;

public class Route {

    HashMap<Integer, Vehicle> vehicles;
    HashMap<String, Stop> stops;
    String routeTag;
    HashMap<String, RouteDirection> routeDirections;

    public Route(HashMap<Integer, Vehicle> vehicles, HashMap<String, Stop> stops, String routeTag, HashMap<String, RouteDirection> routeDirections) {
        this.routeTag = routeTag;
        this.stops = stops;
        this.vehicles = vehicles;
        this.routeDirections = routeDirections;
    }

    public HashMap<Integer, Vehicle> getVehicles() {
        return vehicles;
    }

    public HashMap<String, Stop> getStops() {
        return stops;
    }

    public String getRouteTag() {
        return routeTag;
    }

    public HashMap<String, RouteDirection> getRouteDirections() { return routeDirections; }
}

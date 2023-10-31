package entity;

import java.util.HashMap;

public class Route {

    HashMap<Integer, Vehicle> vehicles;
    HashMap<String, Stop> stops;
    String routeTag;

    public Route(HashMap<Integer, Vehicle> vehicles, HashMap<String, Stop> stops, String routeTag) {
        this.routeTag = routeTag;
        this.stops = stops;
        this.vehicles = vehicles;
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
}

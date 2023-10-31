package entity;

import java.util.HashMap;

public class Route {

    HashMap<Integer, Vehicle> vehicles;
    String routeTag;

    public Route(HashMap<Integer, Vehicle> vehicles, String routeTag) {
        this.routeTag = routeTag;
        this.vehicles = vehicles;
    }

    public HashMap<Integer, Vehicle> getVehicles() {
        return vehicles;
    }

    public String getRouteTag() {
        return routeTag;
    }
}

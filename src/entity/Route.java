package entity;

import java.util.HashMap;

public class Route {

    HashMap<Integer, Vehicle> vehicles;
    int routeId;

    public Route(HashMap<Integer, Vehicle> vehicles, int routeId) {
        this.routeId = routeId;
        this.vehicles = new HashMap<>(vehicles);
    }

    public HashMap<Integer, Vehicle> getVehicles() {
        return vehicles;
    }

    public int getRouteId() {
        return routeId;
    }
}

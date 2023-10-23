package entity;

import java.util.HashMap;

public class Stop {

    String tag;
    Location location;
    HashMap<Integer, Route> routes;

    public Stop(String tag, double latitude, double longitude, HashMap<Integer, Route> routes) {
        this.tag = tag;
        this.location = new Location(latitude, longitude);
        this.routes = new HashMap<>(routes);
    }

    public String getTag() {
        return tag;
    }

    public Location getLocation() { return location; }

    public HashMap<Integer, Route> getRoutes() {
        return routes;
    }
}

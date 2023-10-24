package entity;

import java.util.HashMap;

public class Stop {

    String tag;
    Location location;
    HashMap<String, Route> routes;

    public Stop(String tag, float latitude, float longitude, HashMap<String, Route> routes) {
        this.tag = tag;
        this.location = new Location(latitude, longitude);
        this.routes = routes;
    }

    public String getTag() {
        return tag;
    }

    public Location getLocation() { return location; }

    public HashMap<String, Route> getRoutes() {
        return routes;
    }
}

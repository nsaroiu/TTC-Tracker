package entity;

import java.util.HashMap;

public class Stop {

    String tag;
    float latitude;
    float longitude;
    HashMap<Integer, Route> routes;

    public Stop(String tag, float latitude, float longitude, HashMap<Integer, Route> routes) {
        this.tag = tag;
        this.latitude = latitude;
        this.longitude = longitude;
        this.routes = new HashMap<>(routes);
    }

    public String getTag() {
        return tag;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public HashMap<Integer, Route> getRoutes() {
        return routes;
    }
}

package entity;

import java.util.HashSet;

public class Stop {

    String tag;
    Location location;
    HashSet<String> routeTags;

    public Stop(String tag, float latitude, float longitude, HashSet<String> routeTags) {
        this.tag = tag;
        this.location = new Location(latitude, longitude);
        this.routeTags = routeTags;
    }

    public String getTag() {
        return tag;
    }

    public Location getLocation() { return location; }

    public HashSet<String> getRoutes() {
        return routeTags;
    }
}

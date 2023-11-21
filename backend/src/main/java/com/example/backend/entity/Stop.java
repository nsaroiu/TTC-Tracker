package com.example.backend.entity;

import java.util.HashSet;

public class Stop implements DistanceMeasurable {

    String tag;
    String name;
    Location location;
    HashSet<String> routeTags;

    public Stop(String tag, String name, float latitude, float longitude, HashSet<String> routeTags) {
        this.tag = tag;
        this.name = name;
        this.location = new Location(latitude, longitude);
        this.routeTags = routeTags;
    }

    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() { return location; }

    public HashSet<String> getRouteTags() {
        return routeTags;
    }

    public float distanceTo(DistanceMeasurable other) {
        return location.distanceTo(other.getLocation());
    }
}

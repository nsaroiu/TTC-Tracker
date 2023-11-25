package com.example.backend.use_case.display_stops;

import com.example.backend.entity.Location;

public class StopObject {
    String name;
    String tag;
    Location location;

    public StopObject(String name, String tag, Location location) {
        this.name = name;
        this.tag = tag;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public Location getLocation() {
        return location;
    }
}
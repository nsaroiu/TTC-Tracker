package com.example.backend.use_case.display_stops;

import com.example.backend.entity.Location;

/**
 * Represents a stop object with its name, tag, and location.
 */
public class StopObject {

    String name;
    String tag;
    Location location;

    /**
     * Constructs a StopObject with the specified name, tag, and location.
     *
     * @param name     The name of the stop.
     * @param tag      The tag associated with the stop.
     * @param location The location coordinates of the stop.
     */
    public StopObject(String name, String tag, Location location) {
        this.name = name;
        this.tag = tag;
        this.location = location;
    }

    /**
     * Gets the name of the stop.
     *
     * @return The name of the stop.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the tag associated with the stop.
     *
     * @return The tag of the stop.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Gets the location coordinates of the stop.
     *
     * @return The location of the stop.
     */
    public Location getLocation() {
        return location;
    }
}

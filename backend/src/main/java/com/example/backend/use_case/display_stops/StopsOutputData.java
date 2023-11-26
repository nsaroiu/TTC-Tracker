package com.example.backend.use_case.display_stops;

import com.example.backend.entity.Stop;

import java.util.HashSet;

/**
 * Represents the output data containing a set of stops.
 */
public class StopsOutputData {

    private final HashSet<StopObject> stops;

    /**
     * Constructs StopsOutputData based on the provided set of stops.
     *
     * @param stops The set of stops to be included in the output.
     */
    public StopsOutputData(HashSet<Stop> stops) {
        this.stops = new HashSet<>();
        for (Stop stop : stops) {
            this.stops.add(new StopObject(stop.getName(), stop.getTag(), stop.getLocation()));
        }
    }

    /**
     * Gets the set of stops included in the output.
     *
     * @return The set of StopObjects representing stops.
     */
    public HashSet<StopObject> getStops() {
        return stops;
    }
}

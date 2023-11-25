package com.example.backend.use_case.display_stops;

import com.example.backend.entity.Stop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class StopsOutputData {
    HashSet<StopObject> stops;

    public StopsOutputData(HashSet<Stop> stops) {
        this.stops = new HashSet<>();
        for (Stop stop : stops) {
            this.stops.add(new StopObject(stop.getName(), stop.getTag(), stop.getLocation()));
        }
    }

    public HashSet<StopObject> getStops() {
        return stops;
    }

}


package com.example.backend.use_case.display_stops;

import com.example.backend.entity.Stop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class StopsOutputData {
    HashSet<ArrayList<Object>> stops;

    public StopsOutputData(HashSet<Stop> stops) {
        this.stops = new HashSet<ArrayList<Object>>();
        for (Stop stop : stops) {
            ArrayList<Object> stopData = new ArrayList<Object>();
            stopData.add(stop.getName());
            stopData.add(stop.getTag());
            stopData.add(stop.getLocation());
            this.stops.add(stopData);
        }
    }

    public HashSet<ArrayList<Object>> getStops() {
        return stops;
    }

}

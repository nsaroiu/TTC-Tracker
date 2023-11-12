package com.example.backend.use_case.display_stops;

import com.example.backend.entity.Location;
import com.example.backend.entity.Stop;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class StopsOutputData {

    private HashMap<String, Location> stopsMap;

    public StopsOutputData(HashMap<String, Location> stopsMap) {
        this.stopsMap = stopsMap;
    }

    public HashMap<String, Location> getStopsMap() {
        return stopsMap;
    }

}

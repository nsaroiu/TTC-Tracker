package com.example.backend.use_case.display_stops;

import com.example.backend.entity.Location;

import java.util.HashMap;

public interface StopsImplementation {
    public HashMap<String, Location> execute();
}

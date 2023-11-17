package com.example.backend.use_case.display_stops;

import com.example.backend.data_access.stop.StopDataAccessInterface;
import com.example.backend.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class StopsImplementation implements StopsService {
    @Autowired
    private StopDataAccessInterface stopDataAccessObject;

    public HashMap<String, Location> execute() {
        HashMap<String, Location> allStops = stopDataAccessObject.getAllStopTagsAndLocations();
        return allStops;
    }
}

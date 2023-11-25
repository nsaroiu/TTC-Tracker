package com.example.backend.use_case.display_stops;

import com.example.backend.data_access.stop.StopDataAccessInterface;
import com.example.backend.entity.Location;
import com.example.backend.entity.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;

@Service
public class StopsImplementation implements StopsService {
    @Autowired
    private StopDataAccessInterface stopDataAccessObject;

    public StopsOutputData execute() {
        HashSet<Stop> stops = stopDataAccessObject.getAllStops();
        return new StopsOutputData(stops);
    }
}

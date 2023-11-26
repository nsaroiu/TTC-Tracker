package com.example.backend.use_case.display_stops;

import com.example.backend.data_access.stop.StopDataAccessInterface;
import com.example.backend.entity.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * Implementation of the StopsService interface, providing functionality to retrieve stops.
 */
@Service
public class StopsImplementation implements StopsService {

    @Autowired
    private StopDataAccessInterface stopDataAccessObject;

    /**
     * Executes the stops use case, retrieving all stops from the data access layer.
     *
     * @return StopsOutputData containing the retrieved stops.
     */
    public StopsOutputData execute() {
        HashSet<Stop> stops = stopDataAccessObject.getAllStops();
        return new StopsOutputData(stops);
    }
}

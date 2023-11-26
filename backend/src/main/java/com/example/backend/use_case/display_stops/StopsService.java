package com.example.backend.use_case.display_stops;


/**
 * Interface representing the service for displaying stops.
 */
public interface StopsService {

    /**
     * Executes the stops service and retrieves the output data.
     *
     * @return The output data containing information about stops.
     */
    StopsOutputData execute();
}

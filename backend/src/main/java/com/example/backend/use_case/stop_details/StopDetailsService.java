package com.example.backend.use_case.stop_details;

import com.example.backend.data_access.route.RouteDataAccessInterface;
import com.example.backend.data_access.stop.StopDataAccessInterface;

/**
 * This is the service interface for the stop details use case.
 */

public interface StopDetailsService {

    /**
     * Given an input data, extracts the stopTag and fetches the name of the corresponding stop.
     * Finds the route tags of all the routes that pass through the stop and puts them into a set called routeTags.
     * Creates the output data containing the stopName and the routeTags, and returns it.
     */
     StopDetailsOutputData execute(StopDetailsInputData inputData);
}

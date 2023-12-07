package com.example.backend.use_case.stop_details;

import com.example.backend.entity.Route;
import com.example.backend.entity.Stop;

import java.util.HashSet;

public interface StopDetailsDataAccessInterface {

    /** Returns a set of all stops in the TTC.
     *
     * @return HashSet of Stop Objects.
     */
    HashSet<Stop> getAllStops();

    /** Given a stop tag, return a set of route tags that pass through the stop.
     *
     * @param stopTag String representing stop tag
     * @return HashSet of route tags that pass through the stop
     */
    HashSet<String> getRouteTagsByStopTag(String stopTag);

    /** Returns a Route object for the given route tag.
     *
     * @return Route object for the given route tag
     * @see Route
     */
    Route getRouteByRouteTag(String routeTag);
}

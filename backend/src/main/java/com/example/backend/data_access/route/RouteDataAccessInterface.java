package com.example.backend.data_access.route;

import com.example.backend.entity.Route;

import java.util.HashSet;

public interface RouteDataAccessInterface {

    /** Returns a set of all route tags for TTC.
     *
     * @return HashSet of route tags for TTC
     */
    HashSet<String> getRouteTags();

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

package com.example.backend.data_access.stop;

import com.example.backend.entity.Stop;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;

@Repository
public interface StopDataAccessInterface {

    /** Given a route tag, returns a set of stops on that route.
     *
     * @param routeTag String representing the route tag
     * @return HashSet of stop tags on the route
     */
    HashSet<String> getStopTagsByRouteTag(String routeTag);

    /** Given a route tag, return a list of all stops that the route passes through.
     *
     * @param routeTag String representing route tag
     * @return HashSet of Stop objects that the route passes through
     */
    HashSet<Stop> getStopsByRouteTag(String routeTag);

    /** Returns a set of all stops in the TTC.
     *
     * @return HashSet of Stop Objects.
     */
    HashSet<Stop> getAllStops();

    /** Returns a list of scheduled arrival times of vehicles serving the specified direction at the specified stop.
     *
     * @param stopTag String representing the stop tag
     * @param dirTag  String representing the direction tag
     * @return ArrayList of Strings representing the scheduled arrival times
     */
    ArrayList<String> getScheduledArrivals(String stopTag, String dirTag);

}
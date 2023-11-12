package com.example.backend.data_access.stop;

import com.example.backend.entity.Location;
import com.example.backend.entity.Stop;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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

    /** Returns a HashMap mapping route tags to a set of all stops that the route passes through.
     *
     * @return HashMap mapping route tags to a set of stop tags
     * @see HashMap
     */
    HashMap<String, HashSet<String>> getRouteTagsToStopTags();

    /** Returns a set of all stops in the TTC.
     *
     * @return HashSet of Stop Objects.
     */
    HashSet<Stop> getAllStops();

    /** Returns a list of all stop tags and their respective locations.
     *
     * @return ArrayList of HashMaps mapping stop tags to locations.
     */
    HashMap<String, Location> getAllStopTagsAndLocations();

}

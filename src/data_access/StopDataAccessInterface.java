package data_access;

import entity.Stop;

import java.util.HashMap;
import java.util.HashSet;

public interface StopDataAccessInterface {

    /** Given a route tag, returns a set of stops on that route.
     *
     * @param routeTag String representing the route tag
     * @return HashSet of stop tags on the route
     */
    HashSet<String> getStopTagsByRouteTag(String routeTag);

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

}

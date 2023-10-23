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

    /**
     *
     * @return
     */
    HashMap<String, HashSet<String>> getRouteTagsToStopTags();

    /** Returns a set of all stops in the TTC.
     *
     * @return HashSet of Stop Objects.
     */
    HashSet<Stop> getAllStops();

}

package data_access.route;

import entity.Route;

import java.util.HashMap;
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

    /** Given a stop tag, return a set of all routes that pass through the given stop.
     *
     * @param tag String representing stop tag
     * @return HashSet of Route objects that pass through the stop
     */
    HashSet<Route> getRoutesByStopTag(String tag);

    /** Returns a HashMap mapping stop tags to a set of all routes that pass through the given stop.
     *
     * @return HashMap mapping stop tags to a set of route tags for TTC
     * @see HashMap
     */
    HashMap<String, HashSet<String>> getStopTagsToRouteTags();

}

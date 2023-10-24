package data_access;

import entity.Route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public interface RouteDataAccessInterface {

    /** Returns a list of all route tags for TTC.
     *
     * @return ArrayList of route tags for TTC
     */
    ArrayList<String> getRouteTagList();

    /** Given a stop tag, return a list of all routes that pass through the given stop.
     *
     * @param tag String representing stop tag
     * @return List of Route objects for TTC
     */
    ArrayList<Route> getRoutesByStopTag(String tag);

    /** Returns a HashMap mapping stop tags to a set of all routes that pass through the given stop.
     *
     * @return HashMap mapping stop tags to a set of route tags for TTC
     * @see HashMap
     */
    HashMap<String, HashSet<String>> getStopTagsToRouteTags();

}

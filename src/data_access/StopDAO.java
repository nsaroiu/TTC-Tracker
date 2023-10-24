package data_access;

import entity.Route;
import entity.Stop;
import jdk.jshell.spi.ExecutionControl;
import kotlin.NotImplementedError;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StopDAO implements StopDataAccessInterface {

    /** Given a route tag, returns a set of stops on that route.
     *
     * @param routeTag String representing the route tag
     * @return HashSet of stop tags on the route
     */
    public HashSet<String> getStopTagsByRouteTag(String routeTag) {

        try {
            String[][] params = {{"command", "routeConfig"}, {"r", routeTag}};
            Document doc = UmoiqApiCaller.getRequest(params);

            NodeList nodeList = doc.getElementsByTagName("stop");

            // Initialize HashSet to store stop tags
            HashSet<String> stopTags = new HashSet<>();

            // For each route in the nodeList, add each stop tag to the HashSet
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                stopTags.add(element.getAttribute("tag"));
            }

            return stopTags;

        } catch (NullPointerException | InvalidRequestException e) {
            e.printStackTrace();
        }

        return null;

    }

    /** Returns a HashMap mapping route tags to a set of all stops that the route passes through.
     *
     * @return HashMap mapping route tags to a set of stop tags
     * @see HashMap
     */
    public HashMap<String, HashSet<String>> getRouteTagsToStopTags() {

        RouteDataAccessInterface routeDAO = new RouteDAO();
        // Get list of all route ids
        ArrayList<String> routeTags = routeDAO.getRouteTagList();

        // Get set of all stop tags for each route id and map the route id to set of stop tags
        HashMap<String, HashSet<String>> routeTagToStopTags = new HashMap<>();
        for (String routeTag : routeTags) {
            routeTagToStopTags.put(routeTag, getStopTagsByRouteTag(routeTag));
        }

        // TODO: Above loop is far too slow

        return routeTagToStopTags;

    }

    /** Returns a set of all stops in the TTC.
     *
     * @return HashSet of Stop Objects.
     */
    public HashSet<Stop> getAllStops() {
        RouteDataAccessInterface routeDAO = new RouteDAO();

        // Stores set of stop tags that have already been added
        HashSet<String> previousStops = new HashSet<>();

        // Set of stops to be returned
        HashSet<Stop> stops = new HashSet<>();

        // Get list of all route ids
        ArrayList<String> routeTags = routeDAO.getRouteTagList();

        for (String routeTag : routeTags) {
            try {
                Document doc = UmoiqApiCaller.getRequest(new String[][]{{"command", "routeConfig"}, {"r", routeTag}});

                NodeList nodeList = doc.getElementsByTagName("stop");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);

                    String stopTag = element.getAttribute("tag");

                    // If stop tag has already been added, skip
                    if (previousStops.contains(stopTag)) continue;
                    else previousStops.add(stopTag);

//                    // Create HashMap of route tags to Route objects
//                    ArrayList<Route> routeList = routeDAO.getRoutesByStopTag(stopTag);
//                    HashMap<String, Route> routeMap = new HashMap<>();
//                    for (Route route : routeList) {
//                        routeMap.put(route.getRouteTag(), route);
//                    }

                    // Add new Stop object to HashSet
                    stops.add(new Stop(
                            stopTag,
                            Float.parseFloat(element.getAttribute("lat")),
                            Float.parseFloat(element.getAttribute("lon")),
                            null
                    ));
                }

            } catch (InvalidRequestException | NullPointerException e) {
                e.printStackTrace();
                return null;
            }
        }

        return stops;
    }

}

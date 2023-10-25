package data_access;

import entity.Location;
import entity.Route;
import entity.Stop;
import entity.Vehicle;
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
        VehicleDataAccessInterface vehicleDAO = new VehicleDAO();

        // Stores set of stop tags that have already been added
        HashSet<String> previousStops = new HashSet<>();

        // Map of stopTag to stops
        HashMap<String, Stop> stops = new HashMap<>();

        // Get list of all route ids
        ArrayList<String> routeTags = routeDAO.getRouteTagList();

        for (String routeTag : routeTags) {
            try {
                Document doc = UmoiqApiCaller.getRequest(new String[][]{{"command", "routeConfig"}, {"r", routeTag}});

                NodeList nodeList = doc.getElementsByTagName("stop");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);

                    String stopTag = element.getAttribute("tag");

                    // Map of routeTag to Route to store in Stop object
                    HashMap<String, Route> routes = new HashMap<>();

                    // If stop tag has already been added, update routes
                    if (previousStops.contains(stopTag) && !stops.get(stopTag).getRoutes().containsKey(routeTag)) routes = stops.get(stopTag).getRoutes();

                    if (element.getAttribute("lat").isEmpty()) continue;

                    previousStops.add(stopTag);

                    // Creates a HashMap of vehicle ids to Vehicle objects for Route Object
                    ArrayList<Vehicle> vehicles = vehicleDAO.getVehiclesByRouteTag(routeTag);
                    HashMap<Integer, Vehicle> vehicleMap = new HashMap<>();
                    for (Vehicle vehicle : vehicles) {
                        vehicleMap.put(vehicle.getId(), vehicle);
                    }

                    // Adds new Route object to HashMap
                    routes.put(routeTag, new Route(
                            vehicleMap,
                            routeTag
                    ));

                    System.out.println(routeTag + " " + stopTag);

                    // Add new Stop object to HashSet
                    stops.put(stopTag, new Stop(
                            stopTag,
                            Float.parseFloat(element.getAttribute("lat")),
                            Float.parseFloat(element.getAttribute("lon")),
                            routes
                    ));

                }

            } catch (InvalidRequestException | NullPointerException e) {
                e.printStackTrace();
                return null;
            }
        }

        // Trasnforms HashMap of stopTag to Stop objects into a HashSet of Stop objects
        HashSet<Stop> stopSet = new HashSet<>();
        for (String stopTag : stops.keySet()) {
            stopSet.add(stops.get(stopTag));
        }

        return stopSet;
    }

    public HashMap<String, Location> getAllStopTagsAndLocations() {
        RouteDataAccessInterface routeDAO = new RouteDAO();

        // Get list of all route tags
        ArrayList<String> routeTags = routeDAO.getRouteTagList();

        HashMap<String, Location> stopTagsAndLocations = new HashMap<>();

        for (String routeTag : routeTags) {
            String[][] params = {{"command", "routeConfig"}, {"r", routeTag}};

            NodeList nodeList;

            try {
                Document doc = UmoiqApiCaller.getRequest(params);

                nodeList = doc.getElementsByTagName("stop");

            } catch (InvalidRequestException | NullPointerException e) {
                e.printStackTrace();
                return null;
            }

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                if (element.getAttribute("lat").isEmpty()) continue;
                if (stopTagsAndLocations.containsKey(element.getAttribute("tag"))) continue;

                System.out.println(routeTag + " " + element.getAttribute("tag") + " " + element.getAttribute("lat") + " " + element.getAttribute("lon"));

                stopTagsAndLocations.put(element.getAttribute("tag"), new Location(
                        Float.parseFloat(element.getAttribute("lat")),
                        Float.parseFloat(element.getAttribute("lon"))
                ));

            }
        }

        return stopTagsAndLocations;
    }

}

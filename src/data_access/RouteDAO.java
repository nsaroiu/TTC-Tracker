package data_access;

import entity.Route;
import entity.Vehicle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class RouteDAO implements RouteDataAccessInterface {

    /** Returns a list of all route tags for TTC.
     *
     * @return ArrayList of route tags for TTC
     */
    public ArrayList<String> getRouteTagList() {

        try {
            String[][] params = {{"command", "routeList"}};
            Document doc = UmoiqApiCaller.getRequest(params);

            NodeList nodeList = doc.getElementsByTagName("route");

            ArrayList<String> routes = new ArrayList<>();

            // Add each route tag to the ArrayList
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                routes.add(element.getAttribute("tag"));
            }

            return routes;

        } catch (NullPointerException | InvalidRequestException e) {
            e.printStackTrace();
        }

        return null;

    }

    /** Given a stop tag, return a list of all routes that pass through the given stop.
     *
     * @param tag String representing stop tag
     * @return List of Route objects for TTC
     */
    public ArrayList<Route> getRoutesByStopTag(String tag) {

        ArrayList<Route> routes = new ArrayList<>();

        HashSet<String> routeTags = new HashSet<>(getStopTagsToRouteTags().get(tag));

        for (String routeTag : routeTags) {

            // Generate Map of vehicle ids to vehicles
            HashMap<Integer, Vehicle> vehicles = new HashMap<>();
            VehicleDataAccessInterface vehicleDAO = new VehicleDAO();
            for (Vehicle vehicle : vehicleDAO.getVehiclesByRouteTag(routeTag)) {
                vehicles.put(vehicle.getId(), vehicle);
            }

            // Add new Route object to ArrayList
            routes.add(new Route(
                    vehicles,
                    routeTag));
        }

        return routes;

    }

    /** Returns a HashMap mapping stop tags to a set of all routes that pass through the given stop.
     *
     * @return HashMap mapping stop tags to a set of route tags for TTC
     * @see HashMap
     */
    public HashMap<String, HashSet<String>> getStopTagsToRouteTags() {

        StopDataAccessInterface stopDAO = new StopDAO();
        HashMap<String, HashSet<String>> routeIdToStopTags = stopDAO.getRouteTagsToStopTags();

        // Reverse set to map stop tag to set of route ids
        HashMap<String, HashSet<String>> stopTagToRouteIds = new HashMap<>();
        for (Map.Entry<String, HashSet<String>> entry : routeIdToStopTags.entrySet()) {
            for (String stopTag : entry.getValue()) {
                if (stopTagToRouteIds.containsKey(stopTag)) {
                    stopTagToRouteIds.get(stopTag).add(entry.getKey());
                } else {
                    HashSet<String> routeIds = new HashSet<>();
                    routeIds.add(entry.getKey());
                    stopTagToRouteIds.put(stopTag, routeIds);
                }
            }
        }

        return stopTagToRouteIds;

    }

}

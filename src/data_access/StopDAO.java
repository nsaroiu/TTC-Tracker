package data_access;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StopDAO implements StopDataAccessInterface {

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

    /**
     * @return HashMap mapping route ids to a set of stop tags for TTC
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

}

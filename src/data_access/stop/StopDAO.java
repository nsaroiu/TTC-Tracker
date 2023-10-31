package data_access.stop;

import au.com.bytecode.opencsv.CSVReader;
import data_access.*;
import data_access.route.RouteDAO;
import data_access.route.RouteDataAccessInterface;
import entity.Location;
import entity.Stop;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class StopDAO implements StopDataAccessInterface {

    private final String stopCsvFilename = "src/data/stopData.csv";

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

    /** Given a route tag, return a list of all stops that the route passes through.
     *
     * @param routeTag String representing route tag
     * @return HashSet of Stop objects that the route passes through
     */
    public HashSet<Stop> getStopsByRouteTag(String routeTag) {

        HashSet<Stop> stops = new HashSet<>();
        HashSet<Stop> allStops = getAllStops();

        HashSet<String> stopTags = getStopTagsByRouteTag(routeTag);

        for (Stop stop : allStops) {
            if (stopTags.contains(stop.getTag())) {
                stops.add(stop);
            }
        }

        return stops;

    }

    /** Returns a HashMap mapping route tags to a set of all stops that the route passes through.
     *
     * @return HashMap mapping route tags to a set of stop tags
     * @see HashMap
     */
    public HashMap<String, HashSet<String>> getRouteTagsToStopTags() {

        RouteDataAccessInterface routeDAO = new RouteDAO();
        // Get list of all route ids
        HashSet<String> routeTags = routeDAO.getRouteTags();

        // Initialize CSV reader for Stop data
        FileReader filereader = null;
        try {
            filereader = new FileReader(stopCsvFilename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert filereader != null; // If this fails, the filename needs to be fixed
        CSVReader csvReader = new CSVReader(filereader);

        // Map of routeTag to set of stopTags
        HashMap<String, HashSet<String>> routeTagsToStopTags = new HashMap<>();

        try {
            String[] nextRecord = csvReader.readNext();

            // For each entry in csv, iterate over all routes in the entry
            while ((nextRecord = csvReader.readNext()) != null) {

                String stopTag = nextRecord[0];
                String[] csvRouteTags = nextRecord[3].split(",");

                // Add curr stop to set of stops for each route tag
                for (String routeTag : csvRouteTags) {
                    if (!routeTagsToStopTags.containsKey(routeTag)) {
                        routeTagsToStopTags.put(routeTag, new HashSet<>());
                    }

                    routeTagsToStopTags.get(routeTag).add(stopTag);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assert routeTags.size() == routeTagsToStopTags.size(); // If this fails, the csv file is outdated or incorrect

        return routeTagsToStopTags;

    }

    /** Returns a set of all stops in the TTC.
     *
     * @return HashSet of Stop Objects.
     */
    public HashSet<Stop> getAllStops() {

        // Initialize CSV reader for Stop data
        FileReader filereader = null;
        try {
            filereader = new FileReader(stopCsvFilename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert filereader != null; // If this fails, the filename needs to be fixed
        CSVReader csvReader = new CSVReader(filereader);

        // Initialize HashSet to store Stop objects
        HashSet<Stop> stops = new HashSet<>();

        try {
            String[] nextRecord = csvReader.readNext();

            // Iterate over each entry in csv and create Stop object for each entry
            while ((nextRecord = csvReader.readNext()) != null) {
                String stopTag = nextRecord[0];
                float lat = Float.parseFloat(nextRecord[1]);
                float lon = Float.parseFloat(nextRecord[2]);
                HashSet<String> routeTags = new HashSet<>(List.of(nextRecord[3].split(",")));

                // Add new Stop object to HashSet
                stops.add(new Stop(
                        stopTag,
                        lat,
                        lon,
                        routeTags
                ));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stops;
    }

    /** Returns a list of all stop tags and their respective locations.
     *
     * @return ArrayList of HashMaps mapping stop tags to locations.
     */
    public HashMap<String, Location> getAllStopTagsAndLocations() {

        // Initialize CSV reader for Stop data
        FileReader filereader = null;
        try {
            filereader = new FileReader(stopCsvFilename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert filereader != null; // If this fails, the filename needs to be fixed
        CSVReader csvReader = new CSVReader(filereader);

        // Initialize HashMap to store stop tags and locations
        HashMap<String, Location> stopTagsAndLocations = new HashMap<>();

        try {
            String[] nextRecord = csvReader.readNext();

            while ((nextRecord = csvReader.readNext()) != null) {
                String stopTag = nextRecord[0];
                float lat = Float.parseFloat(nextRecord[1]);
                float lon = Float.parseFloat(nextRecord[2]);

                // Add new Stop object to HashSet
                stopTagsAndLocations.put(stopTag, new Location(lat, lon));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stopTagsAndLocations;
    }

}

package com.example.backend.data_access.route;

import com.example.backend.data_access.stop.StopDAO;
import com.example.backend.data_access.stop.StopDataAccessInterface;
import com.example.backend.entity.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.example.backend.data_access.*;
import com.example.backend.data_access.vehicle.VehicleDAO;
import com.example.backend.data_access.vehicle.VehicleDataAccessInterface;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Repository
public class RouteDAO implements RouteDataAccessInterface {

    private final String stopCsvFilename = "backend/src/main/java/com/example/backend/data/stopData.csv";
    private final String shapesCsvFilename = "backend/src/main/java/com/example/backend/data/shapes.csv";

    /** Returns a set of all route tags for TTC.
     *
     * @return HashSet of route tags for TTC
     */
    public HashSet<String> getRouteTags() {

        try {
            String[][] params = {{"command", "routeList"}};
            Document doc = UmoiqApiCaller.getRequest(params);

            NodeList nodeList = doc.getElementsByTagName("route");

            HashSet<String> routes = new HashSet<>();

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

    /** Given a stop tag, return a set of route tags that pass through the stop.
     *
     * @param stopTag String representing stop tag
     * @return HashSet of route tags that pass through the stop
     */
    public HashSet<String> getRouteTagsByStopTag(String stopTag) {
        // Initialize CSV reader for Stop data
        FileReader filereader = null;
        try {
            filereader = new FileReader(stopCsvFilename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert filereader != null; // If this fails, the filename needs to be fixed
        CSVReader csvReader = new CSVReader(filereader);

        try {
            String[] nextRecord = csvReader.readNext();

            // If the stop tag is found, return the set of route tags
            while ((nextRecord = csvReader.readNext()) != null) {
                if (nextRecord[0].equals(stopTag)) {
                    return new HashSet<>(Arrays.asList(nextRecord[3].split(",")));
                }
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /** Returns a HashMap mapping stop tags to a set of all routes that pass through the given stop.
     *
     * @return HashMap mapping stop tags to a set of route tags for TTC
     * @see HashMap
     */
    public HashMap<String, HashSet<String>> getStopTagsToRouteTags() {

        // Initialize CSV reader for Stop data
        FileReader filereader = null;
        try {
            filereader = new FileReader(stopCsvFilename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert filereader != null; // If this fails, the filename needs to be fixed
        CSVReader csvReader = new CSVReader(filereader);

        // Initialize HashMap to store stop tags and route tags
        HashMap<String, HashSet<String>> stopTagsToRouteTags = new HashMap<>();

        try {
            String[] nextRecord = csvReader.readNext();

            while ((nextRecord = csvReader.readNext()) != null) {
                HashSet<String> routeTags = new HashSet<>(Arrays.asList(nextRecord[3].split(",")));
                stopTagsToRouteTags.put(nextRecord[0], routeTags);
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return stopTagsToRouteTags;
    }

    /** Returns the shape (array of locations) of every TTC routes.
     *
     * @return HashMap mapping route tags to an array of Locations (coordinates) for the route
     */
    public HashMap<String, ArrayList<Location>> getRouteShapes() {
        HashMap<String, ArrayList<Location>> routeShapes = new HashMap<>();

        // Used BufferedReader for massively improved performance
        try (BufferedReader reader = new BufferedReader(new FileReader(shapesCsvFilename))) {
            String line;
            // Skip first row (headers)
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                // Get the routeId, latitude and longitude from the row
                String routeId = row[1];
                float lat = Float.parseFloat(row[2]);
                float lon = Float.parseFloat(row[3]);

                Location location = new Location(lat, lon);

                // Check if the routeId is already in the map
                if (routeShapes.containsKey(routeId)) {
                    // If so, add the location to the existing list (we assume the locations are ordered in the file)
                    routeShapes.get(routeId).add(location);
                } else {
                    // If not, create a new list with the location
                    ArrayList<Location> locations = new ArrayList<>();
                    locations.add(location);
                    routeShapes.put(routeId, locations);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return routeShapes;

    }

    /** Returns a Route object for the given route tag.
     *
     * @return Route object for the given route tag
     * @see Route
     */
    public Route getRouteByRouteTag(String tag) {

        // Create Vehicle Map of vehicles serving the route
        VehicleDataAccessInterface vehicleDAO = new VehicleDAO();
        ArrayList<Vehicle> vehicles = vehicleDAO.getVehiclesByRouteTag(tag);
        HashMap<Integer, Vehicle> vehicleMap = new HashMap<>();
        for (Vehicle vehicle : vehicles) {
            vehicleMap.put(vehicle.getId(), vehicle);
        }

        // Create Stop Map of stops serving the route
        StopDataAccessInterface stopDAO = new StopDAO();
        HashSet<Stop> stopSet = stopDAO.getStopsByRouteTag(tag);
        HashMap<String, Stop> stopMap = new HashMap<>();
        for (Stop stop : stopSet) {
            stopMap.put(stop.getTag(), stop);
        }

        // Create RouteDirection ArrayList of directions for the route
        ArrayList<RouteDirection> routeDirections = new ArrayList<>();

        try {
            String[][] params = {{"command", "routeConfig"}, {"r", tag}};
            Document doc = UmoiqApiCaller.getRequest(params);

            NodeList directionNodeList = doc.getElementsByTagName("direction");

            // Add each direction to the ArrayList
            for (int i = 0; i < directionNodeList.getLength(); i++) {
                Element element = (Element) directionNodeList.item(i);

                NodeList stopNodeList = element.getElementsByTagName("stop");
                ArrayList<String> stopTags = new ArrayList<>();

                for (int j = 0; j < stopNodeList.getLength(); j++) {
                    Element stopElement = (Element) stopNodeList.item(j);
                    stopTags.add(stopElement.getAttribute("tag").split("_")[0]);
                }

                RouteDirection routeDirection = new RouteDirection(
                        stopTags,
                        element.getAttribute("tag"),
                        element.getAttribute("title")
                );

                routeDirections.add(routeDirection);
            }

        } catch (NullPointerException | InvalidRequestException e) {
            e.printStackTrace();
        }

        return new Route(
                vehicleMap,
                stopMap,
                tag,
                routeDirections
        );

    }


}
